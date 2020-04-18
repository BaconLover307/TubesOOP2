package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Card;

/**
 * Kelas untuk event draw kartu
 */
public class DrawEvent implements BaseEvent {
    public Card c;
    public String h; // activePlayer

    /**
     * Membuat event baru
     * @param c kartu baru yang akan diberikan ke hand activePlayer
     * @param h activePlayer
     */
    public DrawEvent(Card c, String h){
        this.c = c;
        this.h = h;
    }

    /**
     * Handler event
     */
    public interface DrawEventHandler {
        void onDrawEvent(DrawEvent e);
    }
    
    /**
     * Menampilkan ke command-line
     */
    public void execute(){
        System.out.println("Card Drawn!");
    }
}
