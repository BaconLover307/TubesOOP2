package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
// import com.avatarduel.model.cards.Deck;
// import com.avatarduel.model.cards.Hand;
// import com.avatarduel.model.cards.BaseEvent;

import java.util.ArrayList;

public class DrawEvent implements BaseEvent {
    
    private Deck d;
    private Hand h;

    public DrawEvent(Deck d, Hand h){
        this.d = d;
        this.h = h;
    }

    public void execute() {
        System.out.println("Draw one card from deck");
        if (d.isEmpty()){
            // TODO LoseCommand.execute(); ????
            // ? What if win on last card? Best to Check lose on changeTurn
            System.out.println("Lost via Deck-out");
        } else {
            Card c = d.drawCard(); 
            h.addCard(c);
        }
    }
}