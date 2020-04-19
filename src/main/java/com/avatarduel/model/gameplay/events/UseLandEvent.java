package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.cards.card.Land;
import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas untuk event player memakai kartu land suatu turn
 * @author Hengky - 13518048
 */
public class UseLandEvent implements BaseEvent {
    
    public Land land;
    public String owner;

    /**
     * Membuat event baru
     * @param L kartu land yang dipilih
     * @param O pemilik kartu land 
     */
    public UseLandEvent(Land L, String O){
        this.land = L;
        this.owner = O;
    }

    /**
     * Interface untuk Handler use land event
     */
    public interface UseLandEventHandler {
        void onUseLandEvent(UseLandEvent e);
    }

    /**
     * Menampilkan pada command-line
     */
    public void execute() {
        System.out.println("Added 1 " + land.getElement() + " Power!");
    }
}