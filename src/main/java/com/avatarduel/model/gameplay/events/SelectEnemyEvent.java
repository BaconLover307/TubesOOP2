package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas ini merupakan kelas event untuk merequest untuk memilih target attack pada saat BATTLE_PHASE
 * @author Gregorius Jovan K. - 13518135
 */
public class SelectEnemyEvent implements BaseEvent {

    public SummonedCharacter SC;

    /**
     * Membuat event baru
     * @param SC kartu yang menyerang
     */
    public SelectEnemyEvent(SummonedCharacter SC) {this.SC = SC;}

    /**
     * Interface untuk handler event
     */
    public interface SelectEnemyEventHandler {
        void onSelectEnemy(SelectEnemyEvent e);
    }

    /**
     * Mencetak pada command-line
     */
    public void execute(){
        System.out.println("Reset Power!");
    }
}
