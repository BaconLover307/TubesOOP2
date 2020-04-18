package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas untuk event permainan selesai
 */
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
        System.out.print("Permainan Selesai! " + getLoser() + "kalah!");
    }
    
}