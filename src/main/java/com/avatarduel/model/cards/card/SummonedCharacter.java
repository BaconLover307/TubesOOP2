package com.avatarduel.model.cards.card;
import java.util.ArrayList;
import com.avatarduel.model.gameplay.events.SkillCardAttachedEvent;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.AttackCharacterEvent;

public class SummonedCharacter implements ICharSummoned, Publisher, Subscriber,
        SkillCardAttachedEvent.SummonCharacterEventHandler, AttackCharacterEvent.AttackCharacterEventHandler {

    private Character CharCard;
    private boolean isAttack; // true jika dalam keadaan attack dan false bila dalam keadaan defense
    private ArrayList<Skill> attachedSkill;
    private GameplayChannel gameplayChannel;

    public SummonedCharacter(Character charCard, boolean isAttack, GameplayChannel gameplayChannel) {
        this.CharCard = charCard;
        this.isAttack = isAttack;
        this.gameplayChannel = gameplayChannel;
        this.gameplayChannel.addSubscriber("ATTACK_CHARACTER_EVENT", this);
    }

    public void rotate() {
        if (this.isAttack) {
            this.isAttack = false;
        } else {
            this.isAttack = true;
        }
    }

    public int getPositionValue() {
        if (isAttack) {
            return CharCard.getAttack();
        } else {
            return CharCard.getDefense();
        }
    }

    public Character getCharCard() {return this.CharCard;}

    public boolean getPosition() {return this.isAttack;}

    public void doAttack(SummonedCharacter target) {
        this.publish("ATTACK_CHARACTER_EVENT", new AttackCharacterEvent(this, target));
    }

    public void destroy() {
        // TODO remove this card
    }

    @Override
    public void onSkillCardAttached(SkillCardAttachedEvent e) {
        if (this == e.charCard) {
            this.attachedSkill.add(e.skillCard);
        }
    }

    @Override
    public void onAttackCharacter(AttackCharacterEvent e) {
        if (this == e.toCard) {
            // TODO : if this posisi attack or e. has power up then do damage to player
            if (e.fromCard.getPosition()) { // karakter yg menyerang harus dalam posisi attack
                if (e.toCard.getPosition()){ // karakter this dalam posisi attack (isAttack == true)
                    if (e.toCard.getPositionValue() < e.fromCard.getPositionValue())
                    {
                        // TO DO : selisih attack ngurangin HP lawan
                    }
                }
                else { // karakter this dalam posisi defense (isAttack == false)
                    if (e.toCard.getPositionValue() < e.fromCard.getPositionValue())
                    {
                        // TO DO : tidak mengurangi HP
                    }
                }
            } 
            this.destroy(); // karakter lawan mati
        }
    }

    @Override
    public void onEvent(BaseEvent event) {
        if(event.getClass() == AttackCharacterEvent.class){
            this.onAttackCharacter((AttackCharacterEvent) event);
        }
        
        if(event.getClass() == SkillCardAttachedEvent.class){
            this.onSkillCardAttached((SkillCardAttachedEvent) event);
        }
    }

    @Override
    public void publish(String topic, BaseEvent event) {
        this.gameplayChannel.sendEvent(topic, event);
    }

}