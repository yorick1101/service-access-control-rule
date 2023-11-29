package yorick.poc.accesscontrol.rule;

import java.util.function.Function;

import yorick.poc.accesscontrol.rule.acl.AccessControlList;

public class RedisRuleService {

 

  public AccessControlList<SmsRequest> createSetAgent(
      String key, Function<SmsRequest, String> valueGetter) {
    return new RedisSetAgent(key, valueGetter);
  }

  private class RedisSetAgent implements AccessControlList<SmsRequest> {

    private final Function<SmsRequest, String> valueGetter;

    public RedisSetAgent(String key, Function<SmsRequest, String> valueGetter) {
      this.valueGetter = valueGetter;
    }

    @Override
    public void add(SmsRequest element) {
      String value = valueGetter.apply(element);
      //redis: add to set
    }

    @Override
    public boolean exists(SmsRequest element) {
      String value = valueGetter.apply(element);
      //if (value != null)  //redis: exists; return true/false
      return false;
    }

    @Override
    public void remove(SmsRequest element) {
      String value = valueGetter.apply(element);
      //redis:remove from set
    }
  }
}
