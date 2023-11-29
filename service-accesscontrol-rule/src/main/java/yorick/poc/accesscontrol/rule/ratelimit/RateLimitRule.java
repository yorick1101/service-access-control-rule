package yorick.poc.accesscontrol.rule.ratelimit;

import lombok.extern.slf4j.Slf4j;
import yorick.poc.accesscontrol.rule.Rule;
import yorick.poc.accesscontrol.rule.SmsRequest;

@Slf4j
public class RateLimitRule implements Rule {

	private final RateLimitRuleValidator validator;

	public RateLimitRule(RateLimitRuleValidator validator) {
		this.validator = validator;
	}

	@Override
	public boolean pass(SmsRequest request) {

		return validator.validate(request);
	}

	@Override
	public final boolean continueIfPass() {
		return true;
	}

	
}
