package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.SummonedCharacter;

/**
 * Kelas untuk event memberikan kartu karakter suatu kartu skill
 */
public class SkillCardAttachedEvent implements BaseEvent {

    public Skill skillCard;
    public SummonedCharacter charCard;
    public int id;

    /**
     * Membuat event baru
     * @param skillCard skill card yang akan dikenakan ke karakter
     * @param charCard kartu karakter yang akan diberi skill
     * @param id id kartu karakter
     */
    public SkillCardAttachedEvent(Skill skillCard, SummonedCharacter charCard, int id){
        this.skillCard = skillCard;
        this.charCard = charCard;
        this.id = id;
    }

    /**
     * Handler event
     */
    public interface SkillCardAttachedEventHandler {
        void onSkillCardAttached(SkillCardAttachedEvent e);
    }
    
    /**
     * Menampilkan ke command-line
     */
    @Override
    public void execute() {
        System.out.println(skillCard.getName() + "attached to " + charCard.getCharCard().getName());
    }
}