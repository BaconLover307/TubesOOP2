package com.avatarduel.model.gameplay;

public interface ISkillSummoned {

    public void attachTarget(SummonedCharacter target);

    public boolean checkTargetLife();

}