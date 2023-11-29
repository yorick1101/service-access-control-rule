package yorick.poc.accesscontrol.rule.acl;

import yorick.poc.accesscontrol.rule.Rule;
import yorick.poc.accesscontrol.rule.SmsRequest;

public class AccessControlListRule implements Rule {

  private final boolean allowAccessOnList;
  private final AccessControlList<SmsRequest> accessControlList;

  public AccessControlListRule(
      boolean allowAccessOnList, AccessControlList<SmsRequest> accessControlList) {
    this.allowAccessOnList = allowAccessOnList;
    this.accessControlList = accessControlList;
  }

  private boolean isInList(SmsRequest request) {
    return accessControlList.exists(request);
  }

  @Override
  public boolean pass(SmsRequest request) {
    if (isInList(request)) {
      return allowAccessOnList;
    } else {
      return !allowAccessOnList;
    }
  }

  @Override
  public final boolean continueIfPass() {
    return !allowAccessOnList;
  }
}
