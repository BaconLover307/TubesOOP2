package com.avatarduel.model.events;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class SpendPowerEvent implements BaseEvent {
    
    private String sender;
    private Element element;
    private int val;

    public SpendPowerEvent(String sender, Element element, int val){
        this.sender = sender;
        this.element = element;
        this.val = val;
    }

    public String getSender(){
        return this.sender;
    }

    public Element getElement(){
        return this.element;
    }

    public int getVal(){
        return this.val;
    }

    public void execute(){
        //
    }

    public class Handler implements BaseEvent {

        private boolean success;

        public Handler(boolean success){
            this.success = success;
        }

        public boolean getSuccess(){
            return this.success;
        }

        public void execute(){
            //
        }
    }
}