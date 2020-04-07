package com.avatarduel.view.events;


public class SummonCharacterEvent implements BaseEvent {
    
    private Character C;

    public SummonCharacterEvent(Character C){
        this.C = C;
    }

    public interface SummonCharacterEventHandler {
        void onSummonCharacterEvent(SummonCharacterEvent e);
    }

    @Override
    public void execute(){

    }
}