package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.player.Player;

/**
 * Kelas untuk event ganti pemain saat ganti turn
 */
public class ChangePlayerEvent implements BaseEvent{

    public Player nextPlayer;

    /**
     * Membuat event change player baru
     * @param nextPlayer pemain selanjutnya
     */
    public ChangePlayerEvent(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    /**
     * Handler untuk change player event
     */
    public interface ChangePlayerEventHandler {
        void onChangePlayer(ChangePlayerEvent e);
    }

    /**
     * Menampilkan pada command-line
     */
    @Override
    public void execute() {
        System.out.println("Player changed! Now " + this.nextPlayer);
    }
}