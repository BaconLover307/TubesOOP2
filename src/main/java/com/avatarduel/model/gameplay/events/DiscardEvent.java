package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas untuk event player memakai kartu land suatu turn
 * @author Hengky - 13518048
 */
public class DiscardEvent implements BaseEvent {
    
    public Card card;
    public String owner;

    /**
     * Membuat event baru
     * @param C kartu yang dipilih
     * @param O pemilik kartu 
     */
    public DiscardEvent(Card C, String O){
        this.card = C;
        this.owner = O;
    }

    /**
     * Interface untuk Handler discard card event
     */
    public interface DiscardEventHandler {
        void onDiscard(DiscardEvent e);
    }

    /**
     * Menampilkan pada command-line
     */
    public void execute() {
        System.out.println(card.getName() + " discarded!");
    }
}