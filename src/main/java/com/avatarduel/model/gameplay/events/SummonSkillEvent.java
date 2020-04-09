package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;

import com.avatarduel.model.cards.card.Skill;

public class SummonSkillEvent implements BaseEvent {
    
    public Skill S;

    public SummonSkillEvent(Skill S){
        this.S = S;
    }

    public interface SummonSkillEventHandler {
        void onSummonSkillEvent(SummonSkillEvent e);
    }

    public void execute(){

    }
}