package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class UseSkillEvent implements BaseEvent {
    
    private Skill skill;

    public UseSkillEvent(Skill S){
        this.skill = S;
    }

    public Skill getSkill(){
        return this.skill;
    }

    public void execute(){

    }
}