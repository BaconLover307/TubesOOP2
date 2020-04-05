package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import com.avatarduel.model.cards.Deck;
import com.avatarduel.model.cards.Hand;

import java.util.ArrayList;

public class DrawEvent implements BaseEvent {
    
    private Deck d;
    private Hand h;

    public DrawEvent(Deck d, Hand h){
        this.d = d;
        this.h = h;
    }

    @Override
    public void execute(){
        System.out.println("Draw one card from deck");
        if (d.isEmpty()){
            //LoseCommand.execute();
            System.out.println("Lost via Deck-out");
        } else {
            Card c = d.drawCard();
            h.addCard(this.c);
        }
    }
}