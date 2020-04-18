package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas untuk kartu ketika di klik
 */
public class CardClickedEvent implements BaseEvent {
    
    /**
     * Membuat event baru
     */
    public CardClickedEvent(){}

    /**
     * Menampilkan ke command-line
     */
    @Override
    public void execute()
    { System.out.println("Card Clicked!");}

    /**
     * Handler event
     */
    public interface CardClickedEventHandler {
        void onCardClicked(CardClickedEvent e);
    }
}