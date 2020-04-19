package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
/**
 * Kelas ini merupakan kelas event untuk memberitahu bahwa attack gagal
 * @author Gregorius Jovan K. - 13518135
 */
public class AttackFailEvent implements BaseEvent {

    public String player;

    /**
     * Membuat event baru
     * @param p pemain yang gagal
     */
    public AttackFailEvent(String p) {this.player = p;}

    /**
     * Interface untuk handler event
     */
    public interface AttackFailEventHandler {
        void onAttackFail(AttackFailEvent e);
    }

    /**
     * Mencetak pada command-line
     */
    public void execute(){
        System.out.println("Attack Failed!");
    }
}

