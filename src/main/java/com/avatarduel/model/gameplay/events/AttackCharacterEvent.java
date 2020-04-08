package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.SummonedCharacter;
import java.util.ArrayList;

public class AttackCharacterEvent implements BaseEvent {

    public SummonedCharacter fromCard;
    public SummonedCharacter toCard;

    public AttackCharacterEvent(SummonedCharacter fromCard, SummonedCharacter toCard){
        this.fromCard = fromCard;
        this.toCard = toCard;
    }


    public interface AttackCharacterEventHandler {
        void onAttackCharacter(AttackCharacterEvent e);
    }
    
    public void execute(){

    }
}