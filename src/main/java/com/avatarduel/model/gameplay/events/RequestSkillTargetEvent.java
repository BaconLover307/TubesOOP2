package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas untuk merequest target dari Skill
 */
public class RequestSkillTargetEvent implements BaseEvent {
    public Skill skill;
    public int id;
    public String owner;

    /**
     * Membuat event request targer skill baru
     * @param skill kartu yang akan menarget
     * @param id id kartu skill
     * @param owner pemilik kartu
     */
    public RequestSkillTargetEvent(Skill skill, int id,  String owner) {
        this.skill = skill;
        this.id = id;
        this.owner = owner;
    }

    /**
     * Handler untuk request skill target event
     */
    public interface RequestSkillTargetEventHandler {
        void onRequestSkillTarget(RequestSkillTargetEvent e);
    }

    /**
     * Menampilkan pada command-line
     */
    @Override
    public void execute() {
        System.out.println("Card " + skill.getName() + " requesting to target!");
    }
}