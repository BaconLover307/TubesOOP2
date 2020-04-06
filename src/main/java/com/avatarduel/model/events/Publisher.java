package com.avatarduel.model.events;

public interface Publisher {
  void publish(String topic, BaseEvent event);
}
