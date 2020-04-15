package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import java.util.ArrayList;

// TODO sesuaiin format
public class EndGameEvent implements BaseEvent {
    
    private String target;
    
    public EndGameEvent(String target){
        this.target = target;
    }

    public String getLoser(){
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