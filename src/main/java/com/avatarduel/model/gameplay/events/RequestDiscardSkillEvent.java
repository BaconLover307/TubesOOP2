package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.SummonedCharacter;

/**
 * Kelas untuk event merequest discard skill saat main phase
 * @author Gregorius Jovan K - 13518
 */
public class RequestDiscardSkillEvent implements BaseEvent {

    public Skill S;

    /**
     * Membuat event baru
     * @param S skill yang di dibuang
     */
    public RequestDiscardSkillEvent(Skill S){
        this.S = S;
    }

    /**
     * Handler event
     */
    public interface RequestDiscardSkillEventHandler {
        public void onRequestDiscardSkillEvent(RequestDiscardSkillEvent e);
    }

    /**
     * Menampilkan ke command-line
     */
    public void execute() {
        System.out.println("Requesting Skill " + S.getName() + " to be discarded.");
    }
}