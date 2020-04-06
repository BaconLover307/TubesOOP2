package com.avatarduel.model.events;

interface Subscriber {
  void onEvent(Event event);
}
