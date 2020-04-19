package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;

<<<<<<< HEAD
/**
 * Kelas untuk event permainan selesai
 */
=======
// TODO sesuaiin format
>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf
public class EndGameEvent implements BaseEvent {
    
    private String target;
    
    /**
     * Membuat event baru
     * @param target player yang kalah
     */
    public EndGameEvent(String target) {this.target = target;}

    /**
     * Mengambil nama player yang kalah
     * @return nama player
     */
    public String getLoser(){
        return this.target;
    }
    
    /**
     * Handler event
     */
    public interface EndGameEventHandler {
        public void onEndGame(EndGameEvent e);
    }

    /**
     * Menampilkan ke command-line
     */
    @Override
    public void execute() {
        System.out.println("Permainan Selesai! " + getLoser() + "kalah!");
    }
    
}