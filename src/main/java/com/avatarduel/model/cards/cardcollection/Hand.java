package com.avatarduel.model.cards.cardcollection;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.events.DrawEvent;
import com.avatarduel.model.gameplay.events.SummonCharacterEvent;
import com.avatarduel.model.gameplay.events.SummonSkillEvent;
import com.avatarduel.model.gameplay.events.UseLandEvent;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.Land;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;

public class Hand extends CardCollection implements Publisher, Subscriber,
        // UseLandEvent.UseLandEventHandler,     
        DrawEvent.DrawEventHandler {

    // TODO Pindahin ke Hand Display
    // private boolean show; // true if card is open
    private boolean usedLand; // true if UseCard(Land) was used this turn
    private GameplayChannel channel;

    public Hand(GameplayChannel channel, String player) {
        super(channel, player);
        // this.show = false;
        this.usedLand = false;
        this.channel = channel;
        channel.addSubscriber("DRAW_EVENT", this);
        // channel.addSubscriber("USE_LAND", this);
    }

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


    public void doSelectChar(Character C) {
        this.publish("SUMMON_CHARACTER", new SummonCharacterEvent(C));
        // TODO ilangin kartu C dari hand
    }

    public void doSelectSkill(Skill S) {
        this.publish("SUMMON_SKILL", new SummonSkillEvent(S));
        // TODO ilangin kartu S dari hand
    }

    public void doSelectLand(Land L) {
        this.publish("USE_LAND", new UseLandEvent(L));
        this.usedLand = true;
        // TODO ilangin kartu L dari hand
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }

    @Override
    public void onEvent(BaseEvent e){
        if (e instanceof DrawEvent){
            this.onDrawEvent((DrawEvent) e);
        // } else if (e instanceof UseLandEvent) {
        //     this.onUseLand((UseLandEvent) e);
        }
    }

    @Override
    public void onDrawEvent(DrawEvent e) {
        if(this.getPlayer().equals(e.h)){
            this.add(e.c);
            this.usedLand = false;
        }
    }

    // @Override
    // public void onUseLand(UseLandEvent e) {
    //     if (this.)
    // }
}