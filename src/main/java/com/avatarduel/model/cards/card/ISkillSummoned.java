package com.avatarduel.model.cards.card;

public interface ISkillSummoned {
    public void attach(SummonedCharacter charTarget);

    public void onCharTargetDiscard();
}