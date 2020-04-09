package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class EndGameEvent implements BaseEvent {
    
    private Player target;
    
    public EndGameEvent(Player target){
        this.target = target;
    }

    public Player getLoser(){
        return this.target;
    }
    
    public void execute(){}
    
    public class Handler implements BaseEvent {
        
        private String msg;
        public Handler (String msg){
            this.msg = msg;
        }

        public String getMessage(){
            return this.msg;
        }

        public void execute(){
            
        }
    }   
}