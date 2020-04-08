package com.avatarduel.model.events;

public interface Subscriber {
  void onEvent(BaseEvent event);
}
