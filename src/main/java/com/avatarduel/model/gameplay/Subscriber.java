package com.avatarduel.model.gameplay;

public interface Subscriber {
  void onEvent(Event event);
}