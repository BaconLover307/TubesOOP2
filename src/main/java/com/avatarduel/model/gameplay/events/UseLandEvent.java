package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class UseLandEvent implements BaseEvent {
    
    private Land land;

    public UseLandEvent(Land L){
        this.land = L;
    }

    public Land getLand(){
        return this.land;
    }

    public void execute(){

    }
}