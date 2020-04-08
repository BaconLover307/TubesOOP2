package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import java.util.ArrayList;

// called from Hand to cast a Skill, either attaching PowerUp or Aura to a Character, or calling DestroyCharacterEvent
// stores the Skill's information
// source: Hand
// target: character
public class UseSkillEvent implements BaseEvent {
    
    private Skill skill;

    public UseSkillEvent(Skill S){
        this.skill = S;
    }

    public Skill getSkill(){
        return this.skill;
    }

    public void execute(){}
}