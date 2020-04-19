package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.view.CardDisplay;

/**
 * Kelas untuk event menampilkan kartu
 */
public class DisplayCardEvent implements BaseEvent {

    public CardDisplay card;

    /**
     * Membuat Event baru
     * @param c kartu yang akan di tampilkan
     */
    public DisplayCardEvent(CardDisplay c) {this.card = c;}

    /**
     * Handler event
     */
    public interface DisplayCardEventHandler {
        void onDisplayCard(DisplayCardEvent e);
    }

    /**
     * Menampilkan ke command-line
     */
    public void execute() {
        System.out.println("Card Displayed!");
    }
}