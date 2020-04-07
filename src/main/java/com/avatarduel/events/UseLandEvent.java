package com.avatarduel.model.events;
import com.avatarduel.model.Element;
import com.avatarduel.model.cards.Land;
import java.util.ArrayList;

public class UseLandEvent implements BaseEvent {

    private Land land;

    public UseLandEvent(Land L){
        this.land = L;
    }

    public void execute(){

    }

    /*
    public class Handler implements BaseEvent {

        private Land land;

        public Handler (Land S){
            this.land = S;
        }

        public Land getLand(){
            return this.land;
        }

        public Element getElement(){
            return this.land.getElement();
        }
    }
    */
}