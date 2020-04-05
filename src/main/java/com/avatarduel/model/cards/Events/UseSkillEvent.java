package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class UseSkillEvent implements BaseEvent {
    
    private boolean used;
    private LandCollection lands;

    public UseLandEvent(LandCollection l){
        this.used = false;
        this.lands = l;
    }

    @Override
    public void execute(){
        System.out.println("Use one Land");
        if (this.used){
            System.out.println("Already used a Land this turn");
        } else {
            lands.addLand(L);
            this.used = true;
        }
    }
}