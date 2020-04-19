package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas untuk kartu ketika di klik
 */
public class SummonCharClickedEvent implements BaseEvent {
    
    public SummonedCharacter SC;

    /**
     * Membuat event baru
     */
    public SummonCharClickedEvent(SummonedCharacter SC) {
        this.SC = SC;
    }

    /**
     * Menampilkan ke command-line
     */
    @Override
    public void execute()
    { System.out.println(SC.getCharCard().getName() + " Card Clicked!");}

    /**
     * Handler event
     */
    public interface SummonCharClickedEventHandler {
        void onSummonCharClicked(SummonCharClickedEvent e);
    }
}