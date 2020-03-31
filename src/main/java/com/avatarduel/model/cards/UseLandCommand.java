package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class UseLandCommand implements CommandBase {
    
    private boolean used;
    private LandCollection lands;

    public UseLandCommand(LandCollection l){
        this.used = false;
        this.lands = l;
    }

    public void reset(){
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

    /*
    @Override
    public void undo(){
        System.out.println("Undo draw card");
        this.c = h.useCard(this.c.getName());
        d.addCard(this.c);
        d.shuffle();
    }
    */
}