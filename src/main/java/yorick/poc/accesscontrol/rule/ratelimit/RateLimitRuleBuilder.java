package yorick.poc.accesscontrol.rule.ratelimit;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.TreeMap;

import yorick.poc.accesscontrol.rule.SmsMessageType;

public class RateLimitRuleBuilder {
	
	private final TreeMap<Duration, DurationRatelimitConfig> configs = new TreeMap<>();
	
	public RateLimitRuleBuilder() {
		
	}
	
	public void addRuleByType(SmsMessageType type, int period, TemporalUnit timeUnit, int limit) {
		Duration duration = Duration.of(period, timeUnit);
		RateLimitConfig config = new RateLimitConfig(duration, limit);
		DurationRatelimitConfig durationConfig  = configs.computeIfAbsent(duration, k-> {return new DurationRatelimitConfig();});
		durationConfig.getTypeConfigs().put(type, config);
	}
	
	public void addGlobalRule(int period, TemporalUnit timeUnit, int limit) {
		Duration duration = Duration.of(period, timeUnit);
		RateLimitConfig config = new RateLimitConfig(duration, limit);
		DurationRatelimitConfig durationConfig  = configs.computeIfAbsent(duration, k-> {return new DurationRatelimitConfig();});
		durationConfig.setGlobalConfig(config);
	}
	
	public TreeMap<Duration, DurationRatelimitConfig> build(){
		if(validate()) {
			return configs;
		}return null;
	}
	
	//For each smstype and global rules 
	
	private boolean validate() {
		return true;
	}
}
