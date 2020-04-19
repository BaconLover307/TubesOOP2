package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;

/**
 * Kelas untuk event flip kartu di hand
 */
public class FlipCardsEvent implements BaseEvent{
    
    public String player;

    /**
     * Membuat event baru
     * @param player player yang akan di flip kartu di hand
     */
    public FlipCardsEvent(String player) {this.player = player;}

    /**
     * Handler event
     */
    public interface FlipCardsEventHandler {
        void onChangePlayer(FlipCardsEvent e);
    }

    /**
     * Menampilkan ke command-line
     */
    @Override
    public void execute() {
        System.out.println("Cards flipped!");
    }
}