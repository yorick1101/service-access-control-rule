package yorick.poc.accesscontrol.rule;



import lombok.Data;

@Data
public class SmsRequest {
  String message;
  String sourceIp;
  String mobile;
  SmsMessageType type;
  long requestTimestmap;
}
