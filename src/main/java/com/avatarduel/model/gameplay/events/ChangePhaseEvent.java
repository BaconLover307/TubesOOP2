package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.Phase;
import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas untuk event pergantian phase
 */
public class ChangePhaseEvent implements BaseEvent {

    public Phase phase;

    /**
     * Membuat event change phase baru
     * @param p phase selanjutnya
     */
    public ChangePhaseEvent(Phase p) {this.phase = p;}

   /**
    * Handler change phase
    */
    public interface ChangePhaseEventHandler {
        void onChangePhase(ChangePhaseEvent e);
    }

    /**
     * Menampilkan pada command-line
     */
    public void execute() {
        System.out.println("Phase changed! Now " + phase.toString());
    }
}
