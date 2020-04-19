package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;

<<<<<<< HEAD
/**
 * Kelas ini merupakan kelas event untuk reset power player pada saat DRAW_PHASE
 * @author Hengky - 13518048
 */
=======
// TODO implementasi
>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf
public class ResetPowerEvent implements BaseEvent {

    public String player;

<<<<<<< HEAD
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
=======
    public interface ReserPowerEventHandler {
        void onResetPower(ResetPowerEvent e);
    }

>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf
    public void execute(){
        System.out.println("Reset Power!");
    }
}