package com.avatarduel.model.cards;

import com.avatarduel.model.cards.Character;
import com.avatarduel.model.cards.Skill;

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