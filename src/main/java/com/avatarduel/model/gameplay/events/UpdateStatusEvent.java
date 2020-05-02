package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas ini merupakan kelas event untuk memberitahu untuk mengupdate health bar
 * @author Gregorius Jovan K. - 13518135
 */
public class UpdateStatusEvent implements BaseEvent {
    /**
     * Membuat event baru
     */
    public UpdateStatusEvent() {}

    /**
     * Interface untuk handler event
     */
    public interface UpdateStatusEventHandler {
        void onUpdateStatus(UpdateStatusEvent e);
    }

    /**
     * Mencetak pada command-line
     */
    @Override
    public void execute(){
//        System.out.println();
    }
}
