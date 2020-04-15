package com.avatarduel.model.cards.card;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.DestroyCharacterEvent;
import com.avatarduel.model.gameplay.events.DiscardSkillEvent;
import com.avatarduel.model.gameplay.events.SkillCardAttachedEvent;
import com.avatarduel.model.gameplay.events.DestroyCharacterEvent.DestroyCharacterEventHandler;
import com.avatarduel.model.gameplay.events.DiscardSkillEvent.DiscardSkillEventHandler;

public class SummonedSkill implements ISkillSummoned, Publisher, Subscriber{
    private Skill SkillCard;
    private SummonedCharacter attachedChar;
    private String owner;
    private GameplayChannel gameplayChannel;

    public SummonedSkill(Skill skill, SummonedCharacter target, String player, GameplayChannel gameplayChannel) {
        this.SkillCard = skill;
        this.owner = player;
        this.gameplayChannel = gameplayChannel;
        this.gameplayChannel.addSubscriber("TARGET_DISCARD_EVENT", this);
        this.gameplayChannel.addSubscriber("CLICKED_EVENT", this);
        this.doAttach(target);
    }

    public Skill getSkillCard() {return this.SkillCard;}
    public SummonedCharacter getTarget() {return this.attachedChar;}
    public String getOwnerName() {return this.owner;}

    //TODO NGGA DIPAKE KARENA SI SUMMONCHAR UDAH NYIMPEN ARRAY SKILL 
    @Override
    public void doAttach(SummonedCharacter charTarget) {
        //this.attachedChar = charTarget;
        //this.publish("SKILL_CARD_ATTACHED_EVENT", new SkillCardAttachedEvent(this.getSkillCard(), charTarget));
    }
/*
    // $ RECEIVE EVENT
    @Override
    public void onDiscardSkill(DiscardSkillEvent e) {
        if (this.SkillCard == e.S && this.attachedChar == e.SC) {
            this.attachedChar = null;
            this.owner = null;
        }
    } */

    @Override
    public void onEvent(BaseEvent event) {
     
    }

    @Override
    public void publish(String topic, BaseEvent event) {
        this.gameplayChannel.sendEvent(topic,event);
    }


}