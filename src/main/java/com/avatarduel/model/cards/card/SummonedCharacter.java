package com.avatarduel.model.cards.card;
import java.util.ArrayList;
import com.avatarduel.model.gameplay.events.SkillCardAttachedEvent;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.gameplay.events.AttackCharacterEvent;


public class SummonedCharacter implements ICharSummoned,
    SkillCardAttachedEvent.SummonCharacterEventHandler,
    AttackCharacterEvent.AttackCharacterEventHandler
     {

    private Character CharCard;
    private boolean isAttack; // true jika dalam keadaan attack dan false bila dalam keadaan defense
    private ArrayList<Skill> attachedSkill;

    public SummonedCharacter(Character charCard, boolean isAttack) {
        this.CharCard = charCard;
        this.isAttack = isAttack;
    }

    public void rotate() {
        if (this.isAttack) {
            this.isAttack = false;
        } else {
            this.isAttack = true;
        }
    }

    public int getPositionValue() {
        if(isAttack){
            return CharCard.getAttack();
        } else {
            return CharCard.getDefense();
        }
    }


    public void doAttack(SummonedCharacter target) {
        // TODO publish new AttackCharacterEvent(this, target)
    }

    public void destroy() {
        // TODO remove this card

    }

    @Override
    public void onSkillCardAttached(SkillCardAttachedEvent e) {
        if(this == e.charCard){
            this.attachedSkill.add(e.skillCard);
        }
    }

    @Override
    public void onAttackCharacter(AttackCharacterEvent e) {
        if(this == e.toCard){
            //TODO : if this posisi attack or e. has power up then do damage to player
            this.destroy();
        }
    }

}