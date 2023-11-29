package yorick.poc.accesscontrol.rule;

public interface Rule {

  boolean pass(SmsRequest request);

  boolean continueIfPass();
}
