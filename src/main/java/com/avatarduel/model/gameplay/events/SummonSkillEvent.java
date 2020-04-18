package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Skill;

/**
 * Kelas untuk event summon skill ke arena
 * @author Hengky - 13518048
 */
public class SummonSkillEvent implements BaseEvent {
    
    public Skill S;

    /**
     * Membuat event baru
     * @param S skill yang disummon
     */
    public SummonSkillEvent(Skill S){
        this.S = S;
    }

    /**
     * Handler event
     */
    public interface SummonSkillEventHandler {
        void onSummonSkillEvent(SummonSkillEvent e);
    }

    /**
     * Menampilkan ke command-line
     */
    public void execute(){
        System.out.println("Skill" + S.getName() + "Summoned!");
    }
}