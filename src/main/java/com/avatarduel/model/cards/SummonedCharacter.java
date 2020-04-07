package com.avatarduel.model.cards;
import java.util.ArrayList;

import com.avatarduel.model.cards.Character;
import com.avatarduel.model.cards.Skill;

public class SummonedCharacter implements ICharSummoned {

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

    public void onBeingAttacked() {
        // TODO Auto-generated method stub

    }

    public void onAttachedSkillCard() {
        // TODO Auto-generated method stub

    }

    public void doAttack(SummonedCharacter target) {
        // TODO Auto-generated method stub

    }

    public void onSkillAttached(Skill skillCard) {
        // TODO Auto-generated method stub

    }

    public void destroy() {
        // TODO Auto-generated method stub

    }

}