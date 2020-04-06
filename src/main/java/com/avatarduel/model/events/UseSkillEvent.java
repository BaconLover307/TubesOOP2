package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class UseSkillEvent implements BaseEvent {
    
    private Skill skill;

    public UseSkillEvent(Skill S){
        this.skill = S;
    }

    @Override
    public void execute(){

    }

    public class Handler implements BaseEvent {

        private Skill skill;

        public Handler (Skill S){
            this.skill = S;
        }

        public Skill getSkill(){
            return this.skill;
        }
    }
}
