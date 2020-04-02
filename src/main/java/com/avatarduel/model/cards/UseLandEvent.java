package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class UseLandEvent implements BaseEvent {
    
    private boolean used;
    private LandCollection lands;
    private Land landUsed;

    public UseLandEvent(LandCollection landCol, Land land) {
        this.used = false;
        this.lands = landCol;
        this.landUsed = land;
    }

    public void onDrawPhase(){
        this.used = false;
    }

    public void execute() {
        if (this.used){
            System.out.println("Already used a Land card on this turn!");
        } else {
            System.out.println("Use one Land card");
            lands.removeLand(this.landUsed);
            // TODO: tambah power ke player
            this.used = true;
        }
    }
}