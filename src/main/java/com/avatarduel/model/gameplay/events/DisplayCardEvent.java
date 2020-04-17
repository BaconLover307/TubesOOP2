package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.view.cards.CardDisplay;

public class DisplayCardEvent implements BaseEvent {
    public CardDisplay card;

    public DisplayCardEvent(CardDisplay c) {this.card = c;}

    public interface DisplayCardEventHandler {
        void onDisplayCard(DisplayCardEvent e);
    }

    public void execute() {
        System.out.println("Card Displayed!");
    }
}