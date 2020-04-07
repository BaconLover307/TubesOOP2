package com.avatarduel.model.cards;
import com.avatarduel.model.events.GameChannel;
import com.avatarduel.model.CardType;

public class SkillCollection extends CardCollection {
    
    private CardType type;

	public SkillCollection(GameChannel channel, String player){
        super(channel, player);
        this.type = CardType.SKILL;
    }

    public void addSkill(Skill S){
        this.addCard(S);
    }

    public void removeSkill(Skill S) {
        this.removeCard(S);
    }

}