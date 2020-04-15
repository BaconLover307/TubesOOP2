package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.SummonedCharacter;

public class DestroyCharacterEvent implements BaseEvent {
    
    public SummonedCharacter SC;

    public DestroyCharacterEvent(SummonedCharacter SC) {
        this.SC = SC;
    }

    public interface DestroyCharacterEventHandler {
        void onDestroyCharacterEvent(DestroyCharacterEvent e);
    }

    public void execute(){
        System.out.println("Character " + SC.getCharCard().getName() + " Destroyed!");
    }
}