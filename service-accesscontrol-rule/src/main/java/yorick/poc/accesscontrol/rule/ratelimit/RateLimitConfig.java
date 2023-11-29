package yorick.poc.accesscontrol.rule.ratelimit;

import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class RateLimitConfig {
  private Duration duration;
  private int limit;
}
