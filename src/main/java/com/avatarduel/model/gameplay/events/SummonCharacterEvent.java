package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;

public class SummonCharacterEvent implements BaseEvent {
    
    public Character C;

    public SummonCharacterEvent(Character C){
        this.C = C;
    }

    public interface SummonCharacterEventHandler {
        void onSummonCharacterEvent(SummonCharacterEvent e);
    }

    public void execute(){
        System.out.println("Character Summoned!");
    }
}