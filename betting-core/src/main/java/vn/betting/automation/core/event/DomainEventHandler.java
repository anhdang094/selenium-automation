package vn.betting.automation.core.event;

@FunctionalInterface
public interface DomainEventHandler<T> {

  void handle(T event);
}
