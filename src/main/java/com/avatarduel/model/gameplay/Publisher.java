package com.avatarduel.model.gameplay;

interface Publisher {
  void publish(String topic, Event event);
}
