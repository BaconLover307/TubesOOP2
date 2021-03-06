package com.avatarduel.model.gameplay;

interface EventChannel {
  // Mengirimkan event ke semua subscriber yang subscribe pada sebuah topic
  public void sendEvent(String topic, BaseEvent event);

  // Menambahkan subscriber ke dalam daftar subscriber pada sebuah topic
  public void addSubscriber(String topic, Subscriber subscriber);
}
