package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas untuk event discard skill saat main phase
 */
public class DiscardSkillEvent implements BaseEvent {

    public DiscardSkillEvent(){

    }

    public interface DiscardSkillEventHandler {
        public void onDiscardSkill(DiscardSkillEvent e);
    }

    public void execute() {
        System.out.println("Skill discarded!");
    }
}