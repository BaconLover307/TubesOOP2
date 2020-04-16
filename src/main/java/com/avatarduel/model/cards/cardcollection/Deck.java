package com.avatarduel.model.cards.cardcollection;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.DrawEvent;
import com.avatarduel.model.gameplay.events.EndGameEvent;
import com.avatarduel.model.gameplay.events.ResetPowerEvent;
import com.avatarduel.model.gameplay.events.CardClickedEvent;

import java.util.ArrayList;
import java.util.Random;

import com.avatarduel.model.cards.card.Card;

public class Deck extends CardCollection implements
    Publisher,
    Subscriber,
    CardClickedEvent.CardClickedEventHandler {

    public Deck(GameplayChannel channel, String player){
        super(channel, player);
    }

	public void shuffle(){
        Random r = new Random();
        int randomNumber;
        Card cardTemp;

        ArrayList<Card> stackTemp = new ArrayList<>();
        randomNumber = r.nextInt(2*this.size()/3+1)+this.size()/3;
        for (int i = 0; i <= randomNumber; i++) {
            cardTemp = this.get(0);
            this.remove(cardTemp);
            stackTemp.add(cardTemp);
        }

        while (stackTemp.size()>0){
            randomNumber = r.nextInt(2*stackTemp.size()/3+1)+stackTemp.size()/3;
            int size = stackTemp.size();
            for (int i = randomNumber; i < size; i++) {
                cardTemp = stackTemp.get(randomNumber);
                stackTemp.remove(cardTemp);
                this.add(cardTemp);
            }
        }
    }

	public void addCard(Card C, int num){
        /* Adds several instances of C to collection */
        for (int i = 0; i < num; i++){
            this.add(C);
        }
    }

	public void doDraw(){
        if(this.isEmpty()){
            this.publish("GAMESTATE", new EndGameEvent(this.getPlayer()));
        } else {
            Card C = this.get(this.size() - 1);
            this.remove(C);
            this.publish("DRAW_EVENT",new DrawEvent(C, this.getPlayer()));
        }
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }

    public void onEvent(BaseEvent e){
        if(e.getClass() == CardClickedEvent.class){
            this.onCardClicked((CardClickedEvent) e);
        }
    }

    @Override
    public void onCardClicked(CardClickedEvent e) {
        if(this.channel.activePlayer == this.player && this.channel.phase.equals("DRAW_PHASE")){
            this.doDraw();
            this.publish("RESET_POWER_EVENT", new ResetPowerEvent());
            this.channel.phase = "MAIN_PHASE";
        }

    }
}