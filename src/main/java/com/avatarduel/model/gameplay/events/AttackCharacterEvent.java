package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.SummonedCharacter;

/**
 * Kelas untuk event menyerang kartu karakter lawan
 */
public class AttackCharacterEvent implements BaseEvent {

    public SummonedCharacter fromCard;
    public SummonedCharacter toCard;

    /**
     * Membuat event baru
     * @param fromCard kartu milik activePlayer
     * @param toCard kartu milik kartu lawan yang ingin diserang
     */
    public AttackCharacterEvent(SummonedCharacter fromCard, SummonedCharacter toCard){
        this.fromCard = fromCard;
        this.toCard = toCard;
    }

    /**
     * Handler untuk event
     */
    public interface AttackCharacterEventHandler {
        void onAttackCharacter(AttackCharacterEvent e);
    }
    
    /**
     * Menampilkan ke layar
     */
    @Override
    public void execute(){
        System.out.println(fromCard.getCharCard().getName() + " attack " + toCard.getCharCard().getName());
    }
}