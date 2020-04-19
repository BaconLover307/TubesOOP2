package com.avatarduel.model.cards.card;

import java.util.ArrayList;

import com.avatarduel.model.Phase;
import com.avatarduel.model.gameplay.events.SkillCardAttachedEvent;
import com.avatarduel.model.gameplay.events.DrawEvent;
import com.avatarduel.model.gameplay.events.CardClickedEvent;
import com.avatarduel.model.gameplay.events.DestroyCharacterEvent;
import com.avatarduel.model.gameplay.events.DiscardSkillEvent;
import com.avatarduel.model.gameplay.events.RepositionCharacterEvent;
import com.avatarduel.model.gameplay.events.AttackPlayerEvent;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.AttackCharacterEvent;

/**
 * Merupakan kelas yang mengimplementasi interface ICharSummoned (kartu yang disummon ke arena)
 */
public class SummonedCharacter implements ICharSummoned, Publisher, Subscriber,
        SkillCardAttachedEvent.SkillCardAttachedEventHandler, 
        AttackCharacterEvent.AttackCharacterEventHandler,
        CardClickedEvent.CardClickedEventHandler,
        DrawEvent.DrawEventHandler,
        RepositionCharacterEvent.RepositionCharacterEventHandler
        {

    private Character CharCard;
    private boolean isAttack; // true jika dalam keadaan attack dan false bila dalam keadaan defense
    private ArrayList<Skill> attachedSkill;
    private String owner;
    private boolean isPowerUp, isAlreadyAttack;
    private int auraValue;
    private GameplayChannel gameplayChannel;

    public SummonedCharacter(Character charCard, boolean isAttack, String player, GameplayChannel gameplayChannel) {
        this.CharCard = charCard;
        this.isAttack = isAttack;
        this.owner = player;
        this.gameplayChannel = gameplayChannel;
        this.auraValue = 0;
        this.isPowerUp = false;
        this.isAlreadyAttack = true;
        this.gameplayChannel.addSubscriber("ATTACK_CHARACTER_EVENT", this);
        this.gameplayChannel.addSubscriber("CLICKED_EVENT", this);
        this.gameplayChannel.addSubscriber("ATTACH_SKILL", this);
        this.gameplayChannel.addSubscriber("DRAW_PHASE", this);
        this.gameplayChannel.addSubscriber("REPOSITION_CHARACTER", this);
    }
    
    public Character getCharCard() {return this.CharCard;}
    public boolean getPosition() {return this.isAttack;}
    public ArrayList<Skill> getAttachedSkill() {return this.attachedSkill;}

    public void rotate() {
        if (this.isAttack) {
            this.isAttack = false;
        } else {
            this.isAttack = true;
        }
    }

    public int getPositionValue() {
        if (isAttack) {
            return CharCard.getAttack() + auraValue;
        } else {
            return CharCard.getDefense() + auraValue;
        }
    }
    

    public void doAttack(SummonedCharacter target) {
        this.publish("ATTACK_CHARACTER_EVENT", new AttackCharacterEvent(this, target));
    }
    public void doAttackPlayer(String target) {
        this.publish("ATTACK_PLAYER_EVENT", new AttackPlayerEvent(this.CharCard.getAttack(), target));
    }

    public void doDiscardSkill(Skill S) {
        this.publish("DISCARD_SKILL", new DiscardSkillEvent(this, S));
    }

    public void destroy() {
        // TODO remove this card and remove every skill attached
        for (Skill skill : attachedSkill) {
            doDiscardSkill(skill);
        }
        this.publish("DESTROY_CHARACTER_EVENT", new DestroyCharacterEvent(this));
    }

    @Override
    public void onSkillCardAttached(SkillCardAttachedEvent e) {
        if (this == e.charCard) {
            this.attachedSkill.add(e.skillCard);
            if(e.skillCard.getClass() == Aura.class){
                this.auraValue = ((Aura) e.skillCard).getPowVal();
            }
            
            if(e.skillCard.getClass() == Destroy.class){
                this.destroy();
            }
            
            if(e.skillCard.getClass() == PowerUp.class){
                this.isPowerUp = true;
            }
        }
    }

    @Override
    public void onAttackCharacter(AttackCharacterEvent e) {
        if (this == e.toCard) {
            if (e.fromCard.getPosition()) { // karakter yg menyerang harus dalam posisi attack
                if (e.toCard.getPositionValue() < e.fromCard.getPositionValue()) // attack/defense value this < attack fromCard
                {
                    if (e.toCard.getPosition()){ // karakter this dalam posisi attack (isAttack == true)

                        this.publish("ATTACK_PLAYER_EVENT", new AttackPlayerEvent(
                            e.fromCard.getPositionValue() - e.toCard.getPositionValue(), 
                            e.toCard.owner
                            ));
                    
                    }
                    else if(e.fromCard.isPowerUp){ 

                        this.publish("ATTACK_PLAYER_EVENT", new AttackPlayerEvent(
                            e.fromCard.getPositionValue() - e.toCard.getPositionValue(), 
                            e.toCard.owner
                            ));
                    
                    }     
                }
                this.destroy();
            }
        }
    }

   
    @Override
    public void publish(String topic, BaseEvent event) {
        this.gameplayChannel.sendEvent(topic, event);
    }

    @Override
    public void onCardClicked(CardClickedEvent e) {
//        if((this.gameplayChannel.phase == Phase.MAIN_PHASE)
//             && this.gameplayChannel.activePlayer.getName() == this.owner){
//            this.rotate();
//            this.publish("REPOSITION_CHARACTER_EVENT", new RepositionCharacterEvent(this));
//        }

        if(this.gameplayChannel.phase == Phase.BATTLE_PHASE){
            if (this.gameplayChannel.activePlayer.getName() == this.owner){
                if(!this.isAlreadyAttack)
                    this.gameplayChannel.lastClickedCard = this;
                // TODO publish  //jika ingin menandakan di board lastclickedcard-nya
            }else{
                if(this.gameplayChannel.lastClickedCard != null){
                    this.publish("ATTACK_CHARACTER_EVENT", new AttackCharacterEvent(this.gameplayChannel.lastClickedCard, this));
                    this.gameplayChannel.lastClickedCard = null;
                }
            }
        }
    }

    @Override
    public void onDrawEvent(DrawEvent e) {
        if((this.gameplayChannel.phase == Phase.DRAW_PHASE)
             && this.gameplayChannel.activePlayer.getName() == this.owner){
            this.isAlreadyAttack = false;
        }
    }

    @Override
    public void onRepositionCharacterEvent(RepositionCharacterEvent e) {
        if (e.SC.equals(this) && e.owner == this.owner) {
            rotate();
        }
    }

    @Override
    public void onEvent(BaseEvent event) {
        if(event instanceof AttackCharacterEvent){
            this.onAttackCharacter((AttackCharacterEvent) event);
        }
        else if(event instanceof SkillCardAttachedEvent){
            this.onSkillCardAttached((SkillCardAttachedEvent) event);
        }
        else if(event instanceof CardClickedEvent){
            this.onCardClicked((CardClickedEvent) event);
        }
        else if(event instanceof DrawEvent){
            this.onDrawEvent((DrawEvent) event);
        }
        else if(event instanceof RepositionCharacterEvent){
            this.onRepositionCharacterEvent((RepositionCharacterEvent) event);
        }
    }



}