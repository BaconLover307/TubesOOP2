package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class SpendPowerEvent implements BaseEvent {
    
    private LandCollection lands;

    public SpendPowerEvent(LandCollection lands){
        this.used = false;
        this.lands = l;
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