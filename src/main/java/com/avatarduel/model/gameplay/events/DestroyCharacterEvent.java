package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.SummonedCharacter;

/**
 * Kelas untuk event destroy character
 */
public class DestroyCharacterEvent implements BaseEvent {
    
    public SummonedCharacter SC;
    public int id;

    /**
     * Membuat event baru
     * @param SC karakter yang di event
     * @param id ID karakter pada board
     */
    public DestroyCharacterEvent(SummonedCharacter SC, int ID) {
        this.SC = SC;
        this.id = ID;
    }

    /**
     * Handler event
     */
    public interface DestroyCharacterEventHandler {
        void onDestroyCharacterEvent(DestroyCharacterEvent e);
    }

    /**
     * Menampilkan ke command-line
     */
    @Override
    public void execute(){
        System.out.println("Character " + SC.getCharCard().getName() + " Destroyed!");
    }
}