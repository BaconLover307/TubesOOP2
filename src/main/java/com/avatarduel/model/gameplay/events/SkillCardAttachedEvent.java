package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.SummonedCharacter;

public class SkillCardAttachedEvent implements BaseEvent {

    public Skill skillCard;
    public SummonedCharacter charCard;

    public SkillCardAttachedEvent(Skill skillCard, SummonedCharacter charCard){
        this.skillCard = skillCard;
        this.charCard = charCard;
    }


    public interface SummonCharacterEventHandler {
        void onSkillCardAttached(SkillCardAttachedEvent e);
    }
    
    public void execute(){

    }
}