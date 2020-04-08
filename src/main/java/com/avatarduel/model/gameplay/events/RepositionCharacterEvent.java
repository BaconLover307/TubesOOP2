package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import java.util.ArrayList;

// When sent to a character, switches their position from defense to attack or vice versa
// Cannot be sent to a character that has already attacked
public class RepositionCharacterEvent implements BaseEvent {
    
    public RepositionCharacterEvent(){}

    public void execute(){}

    public class Handler implements BaseEvent {

        public Handler(){}

        public void execute(){}
    }
}