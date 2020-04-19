package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas ini merupakan kelas event untuk reset power player pada saat DRAW_PHASE
 * @author Hengky - 13518048
 */
public class ResetPowerEvent implements BaseEvent {

    public String player;

    /**
     * Membuat event baru
     * @param player player yang sedang bermain pada suatu turn
     */
    public ResetPowerEvent(String player) {this.player = player;}

    /**
     * Interfance untuk handler event
     */
    public interface ResetPowerEventHandler {
        void onResetPowerEvent(ResetPowerEvent e);
    }

    /**
     * Mencetak pada command-line
     */
    public void execute(){
        System.out.println("Reset Power!");
    }
}