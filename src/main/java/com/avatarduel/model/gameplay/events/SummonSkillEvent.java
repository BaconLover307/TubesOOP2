package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Skill;

/**
 * Kelas untuk event summon skill ke arena
 * @author Hengky - 13518048
 */
public class SummonSkillEvent implements BaseEvent {
    
    public Skill S;
    public int Sid;
    public String owner;

    /**
     * Membuat event baru
     * @param S skill yang disummon
     * @param Sid skill yang disummon
     * @param owner pemilik kartu skill yang disummon
     */
    public SummonSkillEvent(Skill S, int Sid, String owner){
        this.S = S;
        this.Sid = Sid;
        this.owner = owner;
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