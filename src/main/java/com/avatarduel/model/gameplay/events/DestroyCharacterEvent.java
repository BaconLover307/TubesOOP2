package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.SummonedCharacter;

public class DestroyCharacterEvent implements BaseEvent {
    
    public SummonedCharacter SC;

    public DestroyCharacterEvent(SummonedCharacter SC) {
        this.SC = SC;
    }

    public void execute(){
        System.out.println("Character Destroyed!");
    }

    public interface DestroyCharacterEventHandler {
        void onDestroyCharacterEvent(DestroyCharacterEvent e);
    }
}