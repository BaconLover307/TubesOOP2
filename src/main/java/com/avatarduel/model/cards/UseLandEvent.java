package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class UseLandEvent implements BaseEvent {
    
    private boolean used;
    private LandCollection lands;

    public UseLandEvent(LandCollection l){
        this.used = false;
        this.lands = l;
    }

    public void onNewTurn(){
        this.used = false;
    }

    @Override
    public void execute(Land L){
        System.out.println("Use one Land");
        if (this.used){
            System.out.println("Already used a Land this turn");
        } else {
            lands.addLand(L);
            this.used = true;
        }
    }
}