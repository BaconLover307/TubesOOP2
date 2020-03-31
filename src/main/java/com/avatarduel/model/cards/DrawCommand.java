package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import com.avatarduel.model.cards.Deck;
import com.avatarduel.model.cards.Hand;

import java.util.ArrayList;

public class DrawCommand implements CommandBase {
    
    private Deck d;
    private Hand h;

    public DrawCommand(Deck d, Hand h){
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
    /*
    @Override
    public void undo(){
        System.out.println("Undo draw card");
        this.c = h.useCard(this.c.getName());
        d.addCard(this.c);
        d.shuffle();
    }
    */
}