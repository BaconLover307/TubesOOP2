package com.avatarduel.model.cards.cardcollection;

import com.avatarduel.model.cards.card.*;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.events.*;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;

public class Hand extends CardCollection implements Publisher, Subscriber,
        // DiscardEvent.DiscardEventHandler,     
        SummonCharacterEvent.SummonCharacterEventHandler,
        DrawEvent.DrawEventHandler {

    public boolean usedLand; // true if UseCard(Land) was used this turn
    private GameplayChannel channel;

    public Hand(GameplayChannel channel, String player) {
        super(channel, player);
        this.usedLand = false;
        this.channel = channel;
        channel.addSubscriber("DRAW_EVENT", this);
        channel.addSubscriber("SUMMON_CHARACTER", this);
        // channel.addSubscriber("DISCARD", this);
    }

    public boolean isUsedLand() {return usedLand;}

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

    public void removeCard(Card card) {
        this.remove(card);
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

    // public void displayHand() {
    //     if (this.isEmpty()) {
    //         System.out.println("Hand is Empty!");
    //     } else {
    //         for (int i = 0; i < this.size(); i++) {
    //             System.out.println(this.get(i).getName());
    //         }
    //     }
    // }


//    public void doUseChar(Character C) {
//        // TODO cek dulu elemen yg dimiliki player cukup / ngga
//        this.publish("SUMMON_CHARACTER", new SummonCharacterEvent(C));
//        // this.publish("SPEND_POWER_EVENT", new SpendPowerEvent(channel.activePlayer,C.getElement(),C.getPower()))
//        // TODO ilangin kartu C dari hand
//    }

    public void doUseSkill(Skill S) {
        // TODO cek dulu elemen yg dimiliki player cukup / ngga
        this.publish("SUMMON_SKILL", new SummonSkillEvent(S));
        // this.publish("SPEND_POWER_EVENT", new SpendPowerEvent(channel.activePlayer,S.getElement(),S.getPowVal()))
        // TODO ilangin kartu S dari hand
    }

    public void doUseLand(Land L) {
        this.publish("USE_LAND", new UseLandEvent(L, getPlayer()));
        this.usedLand = true;
        this.removeCard(L);
        // TODO ilangin kartu L dari hand
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }

    @Override
    public void onEvent(BaseEvent e){
        if (e instanceof DrawEvent) {
            this.onDrawEvent((DrawEvent) e);
        } else if (e instanceof SummonedCharacter) {
            this.onSummonCharacterEvent((SummonCharacterEvent) e);
        // } else if (e instanceof DiscardEvent) {
        //     this.onDiscard((DiscardEvent) e);
        }
    }

    @Override
    public void onDrawEvent(DrawEvent e) {
        if(this.getPlayer().equals(e.h)){
            this.add(e.c);
            this.usedLand = false;
            e.execute();
        }
    }

    @Override
    public void onSummonCharacterEvent(SummonCharacterEvent e) {
        if (this.getPlayer() == e.owner)
        removeCard(e.C);
    }

    // @Override
    // public void onDiscard(DiscardEvent e) {
    //     if (this.getPlayer().equals(e.owner)) {
    //         this.remove(e.card);
    //     }
    // }
}