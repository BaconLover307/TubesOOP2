package com.avatarduel.model.cards;

import com.avatarduel.model.CardType;

public class SkillCollection extends CardCollection {
    
    private CardType type;

	public SkillCollection() {
        super();
        this.type = CardType.SKILL;
    }
/* 
    public int getLands(Element element){
        int lands = 0;

        for (int i = 0; i < this.size(); i++){
            if (this.getCard(i).getElement() == element){
                lands++;
            }
        }
        return lands;
    } */

    public void addSkill(Skill S){
        this.addCard(S);
    }

    public void removeSkill(Skill S) {
        this.removeCard(S);
    }

}