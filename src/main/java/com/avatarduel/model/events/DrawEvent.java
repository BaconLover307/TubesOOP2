package com.avatarduel.model.events;
import com.avatarduel.model.Element;
import com.avatarduel.model.cards.Card;
import com.avatarduel.model.cards.Deck;
import com.avatarduel.model.cards.Hand;
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
