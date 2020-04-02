/* package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class UseSkillEvent implements BaseEvent {
    
    private boolean used;
    private SkillCollection skills;
    private Skill skillUsed;

    public UseSkillEvent(SkillCollection skills, Skill skill){
        this.used = false;
        this.skills = skills;
        this.skillUsed = skill;
    }

    public void execute(){
        System.out.println("Use SkiLand");
        if (this.used){
            System.out.println("Already used a Land this turn");
        } else {
            lands.addLand(L);
            this.used = true;
        }
    }
} */