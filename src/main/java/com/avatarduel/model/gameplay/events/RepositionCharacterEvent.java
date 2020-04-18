package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas untuk mengubah posisi (attack/defense) suatu kartu karakter
 */
public class RepositionCharacterEvent implements BaseEvent {
    
    public SummonedCharacter SC;

    /**
     * Membuat event baru
     * @param SC kartu karakter yang di posisinya diubah
     */
    public RepositionCharacterEvent(SummonedCharacter SC) {
        this.SC = SC;
    }

    /**
     * Handler event
     */
    public interface RepositionCharacterEventHandler {
        void onRepositionCharacterEvent(RepositionCharacterEvent e);
    }

    /**
     * Menampilkan ke command-line
     */
    public void execute(){
        System.out.println("Position Changed!");
    }

}