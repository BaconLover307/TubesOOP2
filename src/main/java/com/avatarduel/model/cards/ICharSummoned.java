package com.avatarduel.model.cards;

import com.avatarduel.model.cards.Skill;

public interface ICharSummoned {

    // mengubah posisi karakter dari menyerang jadi bertahan, atau sebaliknya
    public void rotate();

    // bila dalam posisi menyerang, mengembalikan attack.
    // bila dalam posisi bertahan, mengembalikan defense
    public int getPositionValue();

    public void onBeingAttacked();

    public void onAttachedSkillCard();

    public void doAttack(SummonedCharacter target);

    public void onSkillAttached(Skill skillCard);

}