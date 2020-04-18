package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.SummonedCharacter;

/**
 * Kelas untuk event destroy character
 */
public class DestroyCharacterEvent implements BaseEvent {
    
    public SummonedCharacter SC;

    /**
     * Membuat event baru
     * @param SC karakter yang di event
     */
    public DestroyCharacterEvent(SummonedCharacter SC) {
        this.SC = SC;
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