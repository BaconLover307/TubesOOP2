package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Card;

/**
 * Kelas untuk merequest Summon Character atau Skill
 */
public class RequestSummonEvent implements BaseEvent{

    public Card card;
    public String owner;

    /**
     * Membuat event request summon baru
     * @param card kartu yang akan disummon
     * @param owner pemilik kartu
     */
    public RequestSummonEvent(Card card, String owner) {
        this.card = card;
        this.owner = owner;
    }

    /**
     * Handler untuk request summon event
     */
    public interface RequestSummonEventHandler {
        void onRequestSummon(RequestSummonEvent e);
    }

    /**
     * Menampilkan pada command-line
     */
    @Override
    public void execute() {
        System.out.println("Card " + card.getName() + " requested to be summoned by " + this.owner + "!");
    }
}