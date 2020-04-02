/* package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class SpendPowerEvent implements BaseEvent {
    
    private LandCollection lands;
    private boolean used;

    public SpendPowerEvent(LandCollection lands){
        this.lands = lands;
        this.used = false;
    }

    public void execute(){ // TODO: BENERIN
        System.out.println("Use one Land");
        if (this.used){
            System.out.println("Already used a Land card this turn");
        } else {
            lands.addLand(L);
            this.used = true;
        }
    }
} */