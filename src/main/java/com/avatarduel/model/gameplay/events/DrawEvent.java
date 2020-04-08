package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.Element;

import java.util.ArrayList;

// sender: Phase
// target: Hand
public class DrawEvent implements BaseEvent {

    public DrawEvent(){}

    public void execute(){}

    public class Handler implements BaseEvent {

        private Card drawnCard;
        
        public Handler(Card C){
            this.drawnCard = C;
        }

        public Card getCard(){
            return this.drawnCard;
        }

        public void execute(){}
    }
}
