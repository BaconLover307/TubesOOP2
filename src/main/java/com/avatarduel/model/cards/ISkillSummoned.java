package com.avatarduel.model.cards;

import com.avatarduel.model.cards.SummonedCharacter;

public interface ISkillSummoned {
    public void attach(SummonedCharacter charTarget);

    public void onCharTargetDiscard();
}