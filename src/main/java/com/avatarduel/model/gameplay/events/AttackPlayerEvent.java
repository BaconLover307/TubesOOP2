package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class AttackPlayerEvent implements BaseEvent {
    
    private int atk;

    public AttackPlayerEvent(int atk){
        this.atk = atk;
    }

    public int getAttack(){
        return this.atk;
    }

    public void execute(){

    }
}
