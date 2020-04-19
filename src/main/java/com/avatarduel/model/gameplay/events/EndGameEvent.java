package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;

public class EndGameEvent implements BaseEvent {
    
    private String target;
    
    public EndGameEvent(String target) {this.target = target;}

    public String getLoser(){
        return this.target;
    }
    
    public interface EndGameEventHandler {
        public void onEndGame(EndGameEvent e);
    }

    public void execute() {

    }
    
}