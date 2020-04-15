package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.SummonedCharacter;

/**
 * Kelas untuk event discard skill saat main / battle phase
 */
public class DiscardSkillEvent implements BaseEvent {

    public SummonedCharacter SC;
    public Skill S;

    public DiscardSkillEvent(SummonedCharacter SC, Skill S){
        this.SC = SC;
        this.S = S;
    }

    public interface DiscardSkillEventHandler {
        public void onDiscardSkill(DiscardSkillEvent e);
    }

    public void execute() {
        System.out.println("Skill " + S.getName() + " discarded!");
    }
}