package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import com.avatarduel.model.cards.card.SummonedCharacter;

/**
 * Kelas untuk event attack player
 */
public class AttackPlayerEvent implements BaseEvent {

    public int amount;
    public String target;

    /**
     * Membuat event baru
     * @param amount besar ke hp yang dikenakan
     * @param target nama player lawan
     */
    public AttackPlayerEvent(int amount, String target){
        this.amount = amount;
        this.target = target;
    }

    /**
     * Handler event
     */
    public interface AttackPlayerEventHandler {
        void onAttackPlayer(AttackPlayerEvent e);
    }
    
    /**
     * Menampilkan ke command-line
     */
    @Override
    public void execute(){
        System.out.println(target + "'s HP reduced by " + amount);
    }
}
