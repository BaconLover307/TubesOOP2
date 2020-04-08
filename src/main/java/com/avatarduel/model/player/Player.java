package com.avatarduel.model.player;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.events.AttackPlayerEvent;
import com.avatarduel.model.cards.cardcollection.Deck;
import com.avatarduel.model.cards.cardcollection.Hand;

public class Player implements IPlayer, Publisher, Subscriber {
    protected String name;
    protected Deck deck;
    protected Hand hand;
    // protected Board board;
    protected int health;
    protected Power powers;
    protected GameplayChannel channel;

    public Player(String name, int health, GameplayChannel channel) {
        this.name = name;
        this.deck = new Deck();
        this.hand = new Hand();
        // this.board = new Board();
        this.health = health;
        this.powers = new Power(channel, name);
        this.channel = channel;
    }

    public void onBeingAttacked(AttackPlayerEvent e) {
        // TODO Auto-generated method stub
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }

    public void onEvent(BaseEvent e){
        /*
        if (e instanceof EventClass){
            //call function
        } 
        */
    }
}
