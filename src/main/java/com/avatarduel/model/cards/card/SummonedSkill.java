package com.avatarduel.model.cards.card;


public class SummonedSkill implements ISkillSummoned {
    private Skill SkillCard;
    private SummonedCharacter attachedChar;

    public SummonedSkill(Skill skillCard) {
        this.SkillCard = SkillCard;
    }

    public void attach(SummonedCharacter charTarget) {
        this.attachedChar = charTarget;
    }

    public void onCharTargetDiscard() {
        // TODO saat character target di discard, buang kartu
    }


}