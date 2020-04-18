package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.SummonedCharacter;

/**
 * Kelas untuk event discard skill saat main / battle phase
 * @author Hengky - 13518048
 */
public class DiscardSkillEvent implements BaseEvent {

    public SummonedCharacter SC;
    public Skill S;

    /**
     * 
     * @param SC character yang skillnya (S) dibuang
     * @param S skill yang di dibuang
     */
    public DiscardSkillEvent(SummonedCharacter SC, Skill S){
        this.SC = SC;
        this.S = S;
    }

    /**
     * Handler event
     */
    public interface DiscardSkillEventHandler {
        public void onDiscardSkillEvent(DiscardSkillEvent e);
    }

    /**
     * Menampilkan ke command-line
     */
    public void execute() {
        System.out.println("Skill " + S.getName() + " discarded!");
    }
}