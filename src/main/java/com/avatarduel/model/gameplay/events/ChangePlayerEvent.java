package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;


public class ChangePlayerEvent implements BaseEvent{
    public String nextPlayer;

    public ChangePlayerEvent(String nextPlayer) {this.nextPlayer = nextPlayer;}

    public interface ChangePlayerEventHandler {
        void onChangePlayer(ChangePlayerEvent e);
    }

    @Override
    public void execute() {
        System.out.println("Player changed! Now " + this.nextPlayer);
    }
}