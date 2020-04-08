package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import com.avatarduel.model.cards.card.Card;

import java.util.ArrayList;

public class DrawEvent implements BaseEvent {

    public DrawEvent(int player){

    }

    public void execute(){

    }

    public class Handler implements BaseEvent {

        private Card drawnCard;
        
        public Handler(Card C){
            this.drawnCard = C;
        }

        public void execute(){

        }

        public Card getCard(){
            return this.drawnCard;
        }
    }
}
