package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;


public class FlipCardsEvent implements BaseEvent{
    public String player;

    public FlipCardsEvent(String player) {this.player = player;}

    public interface FlipCardsEventHandler {
        void onChangePlayer(FlipCardsEvent e);
    }

    @Override
    public void execute() {
        System.out.println("Cards flipped!");
    }
}