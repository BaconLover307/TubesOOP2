package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class EndGameEvent implements BaseEvent {
    
    private String target;
    public EndGameEvent(String target){
        this.target = target;
    }
    /*
    @Override
    public void execute(Land L){

    }

    public class Handler implements BaseEvent {
        
        private String msg;
        public Handler (String msg){
            this.msg = msg;
        }

        public String getMessage(){
            return this.msg;
        }
    }
    */
}