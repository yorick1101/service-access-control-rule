package yorick.poc.accesscontrol.rule.ratelimit;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import yorick.poc.accesscontrol.rule.SmsMessageType;
import yorick.poc.accesscontrol.rule.SmsRequest;

public class RateLimitRuleValidator {

  private final TimeWindowRecorder recorder;
  private final TreeMap<Duration, DurationRatelimitConfig> configs;
  
  public RateLimitRuleValidator(TimeWindowRecorder recorder, final TreeMap<Duration, DurationRatelimitConfig> configs) {
	  this.recorder = recorder;
	  this.configs = configs;
  }

  public boolean validate(SmsRequest newRequest) {
    recorder.add(newRequest);
    // Should start from latest
    List<SmsRequest> list = recorder.list();
    Counter counter = new Counter();
    // loop all durations
    Iterator<Duration> durations = configs.keySet().iterator();
    Duration currentDuration = durations.next();
    long startTimestamp = newRequest.getRequestTimestmap() - currentDuration.toMillis();
    boolean passed = true;
    for (SmsRequest request : list) {
      if (request.getRequestTimestmap() > startTimestamp) {
        counter.addOne(request.getType());
      } else {
        passed = checkCounter(newRequest.getType(), counter, configs.get(currentDuration));
        if (passed) {
          // next duration
          if (!durations.hasNext()) {
            break;
          } else {
            currentDuration = durations.next();
            startTimestamp = newRequest.getRequestTimestmap() - currentDuration.toMillis();
          }
        } else {
          break;
        }
      }
    }
    // delete out of scope records
    currentDuration = configs.lastKey();
    startTimestamp = newRequest.getRequestTimestmap() - currentDuration.toMillis();
    recorder.deleteBefore(startTimestamp);
    
    return passed;
  }

  private boolean checkCounter(
      SmsMessageType type, Counter counter, DurationRatelimitConfig config) {
    RateLimitConfig globalConfig = config.getGlobalConfig();
    RateLimitConfig typeConfig = config.getTypeConfigs().get(type);
    if ((typeConfig != null && check(typeConfig.getLimit(), counter.getCountBySmsMessageType(type)))
        && (globalConfig != null && check(globalConfig.getLimit(), counter.getGlobalCount()))) {
      return true;
    }
    return false;
  }

  private boolean check(int limit, int count) {
    if (count > limit) return false;
    return true;
  }
}
