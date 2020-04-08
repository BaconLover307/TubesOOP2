package com.avatarduel.model.cards.cardcollection;
import com.avatarduel.model.Element;
import com.avatarduel.model.cards.card.Flippable;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.events.DrawEvent;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;

import java.util.ArrayList;

public class Hand extends CardCollection implements Flippable, Publisher, Subscriber {
    private boolean show; // Jika kartu terbuka, maka true

    // public Hand(GameChannel channel, String player) {
    //     super(channel, player);
    //     this.show = false;
    // }

    public int findCard(String name) {
        int i = 0;

        while ((i < this.size()) && (!this.get(i).getName().equals(name))) {
            i++;
        }
        if (this.get(i).getName().equals(name)) {
            return i;
        } else {
            return -999; // null can't converted to int
        }
    }

    public Card useCard(String name) {
        /* Diasumsi bahwa ada kartu dengan nama name dalam hand */
        /* KAMUS */
        int index = findCard(name);

        /* ALGORITMA */
        Card C = this.get(index);
        this.remove(C);
        return C;
    }

    public void displayHand() {
        if (this.isEmpty()) {
            System.out.println("Hand is Empty!");
        } else {
            for (int i = 0; i < this.size(); i++) {
                System.out.println(this.get(i).getName());
            }
        }
    }

    // Untuk menutup kartu
    public void flipOpen() {
        this.show = true;
    }

    public void flipClose() {
        this.show = false;
    }

    /*
    public UseCard(Character C, String target){
        this.publish(target, new SummonCharacterEvent(C));
    }

    public UseCard(Land C, String target){
        this.publish(target, new UseLandEvent(C));
    }

    public UseCard(Skill C, String target){
        this.publish(target, new UseSkillEvent(C));
    }
    */

    public void onDrawEvent(DrawEvent.Handler e){
        this.addCard(e.getCard());
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }

    public void onEvent(BaseEvent e){
        if (e instanceof DrawEvent.Handler){
            this.onDrawEvent((DrawEvent.Handler)e);
        } 
    }
}