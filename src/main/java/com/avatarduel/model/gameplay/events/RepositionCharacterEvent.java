package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.gameplay.BaseEvent;

// When sent to a character, switches their position from defense to attack or vice versa
// Cannot be sent to a character that has already attacked

public class RepositionCharacterEvent implements BaseEvent {
    
    SummonedCharacter SC;

    public RepositionCharacterEvent(SummonedCharacter SC) {
        this.SC = SC;
    }

    public interface RepositionCharacterEventHandler {
        void onRepositionCharacterEvent(RepositionCharacterEvent e);
    }

    public void execute(){
        System.out.println("Position Changed!");
    }

}