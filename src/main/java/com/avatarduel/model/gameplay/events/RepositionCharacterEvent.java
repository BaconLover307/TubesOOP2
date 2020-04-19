package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.gameplay.BaseEvent;

<<<<<<< HEAD
/**
 * Kelas untuk mengubah posisi (attack/defense) suatu kartu karakter
 */
=======
// When sent to a character, switches their position from defense to attack or vice versa
// Cannot be sent to a character that has already attacked
// TODO implementasi
>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf
public class RepositionCharacterEvent implements BaseEvent {
    
    public SummonedCharacter SC;
    public String owner;

<<<<<<< HEAD
    /**
     * Membuat event baru
     * @param SC kartu karakter yang di posisinya diubah
     * @param SC pemilik kartu
     */
    public RepositionCharacterEvent(SummonedCharacter SC, String owner) {
        this.SC = SC;
        this.owner = owner;
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
=======
    public interface RepositionCharacterEventHandler {
        void onRepositionCharacter(RepositionCharacterEvent e);
    }

>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf
    public void execute(){
        System.out.println("Position Changed!");
    }

}