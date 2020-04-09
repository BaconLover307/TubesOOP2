package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;

public class CardClickedEvent implements BaseEvent {
    
    public CardClickedEvent(){}

    public void execute(){}

    public interface CardClickedEventHandler {
        void onCardClicked(CardClickedEvent e);
    }
}