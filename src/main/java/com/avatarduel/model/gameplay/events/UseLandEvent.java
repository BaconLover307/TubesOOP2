package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.cards.card.Land;
import com.avatarduel.model.gameplay.BaseEvent;

// called by Hand to add one power, usable once per turn
// source: Hand
// target: Power
// TODO sesuaiin format
public class UseLandEvent implements BaseEvent {
    
    public Land land;

    public UseLandEvent(Land L){
        this.land = L;
    }

    public interface UseLandEventHandler {
        void onUseLand(UseLandEvent e);
    }

    public void execute() {
        System.out.println("Added 1 " + land.getElement() + " Power!");
    }
}