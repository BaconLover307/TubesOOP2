package com.avatarduel.model.gameplay;

import java.util.ArrayList;
import java.util.HashMap;

import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.player.Player;

public class GameplayChannel implements EventChannel {
    
    private HashMap<String, ArrayList<Subscriber>> listSubscriber;
    public String phase;
    public Player activePlayer;
    public SummonedCharacter lastClickedCard;

    public GameplayChannel() {
        this.listSubscriber = new HashMap<>();
        this.phase = "GAME_INIT";
      }
    
    public void addSubscriber(String topic, Subscriber s) {
    if(this.listSubscriber.get(topic) == null){
        this.listSubscriber.put(topic, new ArrayList<>());
    }
    this.listSubscriber.get(topic).add(s);
    }

    public void sendEvent(String topic, BaseEvent event) {
      ArrayList<Subscriber> list = this.listSubscriber.get(topic);
      if(list != null)
        for (Subscriber subscriber : list) {
          subscriber.onEvent(event);
        }
    }

}