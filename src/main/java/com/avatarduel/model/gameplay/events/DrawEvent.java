package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.cards.card.Card;
// import com.avatarduel.model.cards.cardcollection.Hand;
// import com.avatarduel.model.Element;
// import com.avatarduel.model.cards.card.Card;

// import java.util.ArrayList;

// sender: Phase
// target: Hand
public class DrawEvent implements BaseEvent {
    public Card c;
    public Player h;

    public DrawEvent(Card c, Player h){
        this.c = c;
        this.h = h;
    }


    public interface DrawEventHandler {
        void onDrawEvent(DrawEvent e);
    }
    
    public void execute(){

    }
}
