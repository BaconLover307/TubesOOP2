package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;

// When sent to a character, switches their position from defense to attack or vice versa
// Cannot be sent to a character that has already attacked
// TODO implementasi
public class RepositionCharacterEvent implements BaseEvent {
    
    public RepositionCharacterEvent(){}

    public interface RepositionCharacterEventHandler {
        void onRepositionCharacter(RepositionCharacterEvent e);
    }

    public void execute(){
        System.out.println("Position Changed!");
    }

}