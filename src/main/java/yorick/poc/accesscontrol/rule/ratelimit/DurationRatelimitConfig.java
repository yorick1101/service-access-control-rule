package yorick.poc.accesscontrol.rule.ratelimit;

import java.util.Map;
import  java.util.HashMap;

import lombok.Data;
import yorick.poc.accesscontrol.rule.SmsMessageType;

@Data	
class DurationRatelimitConfig {
  private Map<SmsMessageType, RateLimitConfig> typeConfigs = new HashMap<>();
  private RateLimitConfig globalConfig = null;
}