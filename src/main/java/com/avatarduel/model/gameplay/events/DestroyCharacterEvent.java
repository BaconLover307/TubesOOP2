package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import java.util.ArrayList;

// sender: Character, Skill
// target: Board
// TODO apakah ini skill ?
public class DestroyCharacterEvent implements BaseEvent {
    
    public DestroyCharacterEvent(){}

    public void execute(){}

    public interface DestroyCharacterEventHandler {
        void onDestroyCharacter(DestroyCharacterEvent e);
    }
}