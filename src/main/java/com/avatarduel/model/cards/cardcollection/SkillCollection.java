package com.avatarduel.model.cards.cardcollection;

import com.avatarduel.model.CardType;
import com.avatarduel.model.cards.card.Skill;

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