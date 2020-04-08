package com.avatarduel.model.gameplay;

public interface Subscriber {
  void onEvent(BaseEvent event);
}