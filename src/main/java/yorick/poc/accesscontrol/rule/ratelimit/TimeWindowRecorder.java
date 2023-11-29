package yorick.poc.accesscontrol.rule.ratelimit;

import java.util.List;

import yorick.poc.accesscontrol.rule.SmsRequest;

/**
 * Keep records in a given time window
 *
 * @author yorick
 */
interface TimeWindowRecorder {

  void add(SmsRequest newRequest);

  void deleteBefore(long startTimestamp);

  List<SmsRequest> list();
}