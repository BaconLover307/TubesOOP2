package com.avatarduel.model.gameplay;

public interface Publisher {
    void publish(String topic, BaseEvent event);
}