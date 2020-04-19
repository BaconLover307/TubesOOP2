package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Character;

/**
 * Kelas untuk event summon kartu character ke layar
 * @author Hengky - 13518048
 */
public class SummonCharacterEvent implements BaseEvent {
    
    public Character C;
    public int id;
    public String owner;

    /**
     * Membuat event baru
     * @param C kartu karakter yang di summon
     */
    public SummonCharacterEvent(Character C, int id, String owner){
        this.C = C;
        this.id = id;
        this.owner = owner;
    }

    /**
     * Handler event
     */
    public interface SummonCharacterEventHandler {
        void onSummonCharacterEvent(SummonCharacterEvent e);
    }

    /**
     * Menampilkan ke command-line
     */
    public void execute(){
        System.out.println("Character " + C.getName() + " summoned by " + owner + "!");
    }
}