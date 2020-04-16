package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import com.avatarduel.model.player.Player;

// Sent to Power to call usePower, remembers the Element and number of Power required, remembers the sender for return address
// returns handler with boolean, true if there was enough power of the required element
// source: Hand
// target: Power
//TODO sesuaiin format
public class SpendPowerEvent implements BaseEvent {


    public String sender;
    public Element element;
    public int amount;

    public SpendPowerEvent(String sender, Element element, int val){
        //this.sender = sender;
        this.element = element;
        this.amount = amount;
    }
/*
    public String getSender(){
        return this.sender;
    }

    public Element getElement(){
        return this.element;
    }

    public int getVal(){
        return this.val;
    }
*/
    public interface SpendPowerEventHandler{
        public void onSpendPowerEvent(SpendPowerEvent e);
    } 

    public void execute(){}

    /*
    public class Handler implements BaseEvent {

        private boolean success;

        public Handler(boolean success){
            this.success = success;
        }

        public boolean getSuccess(){
            return this.success;
        }

        public void execute(){}
    } */
}