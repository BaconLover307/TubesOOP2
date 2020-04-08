package com.avatarduel.model.events;
import java.util.ArrayList;

public class EndGameEvent implements BaseEvent {
    
    private String target;
    public EndGameEvent(String target){
        this.target = target;
    }
    
    public void execute(){

    }
    
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