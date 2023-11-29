package yorick.poc.accesscontrol.rule.ratelimit;

import java.util.HashMap;
import java.util.Map;

import yorick.poc.accesscontrol.rule.SmsMessageType;

class Counter {
  private Map<SmsMessageType, Integer> typeCounter = new HashMap<>();
  private int counter;

  public void addOne(SmsMessageType type) {
    typeCounter.compute(type, (k, v) -> (v == null) ? Integer.valueOf(1) : v + 1);
    counter++;
  }

  public int getGlobalCount() {
    return counter;
  }

  public int getCountBySmsMessageType(SmsMessageType type) {
    return typeCounter.get(type);
  }
}