package yorick.poc.accesscontrol.rule.acl;

public interface AccessControlList<T> {
  void add(T element);

  void remove(T element);

  boolean exists(T element);
}
