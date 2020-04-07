package com.avatarduel.model.player;
import com.avatarduel.model.cards.Deck;
import com.avatarduel.model.cards.Hand;
import com.avatarduel.model.events.BaseEvent;
import com.avatarduel.model.events.GameChannel;
import com.avatarduel.model.events.Publisher;
import com.avatarduel.model.events.Subscriber;

public class Player implements 
    Publisher,
    Subscriber{
    
    protected String name;
    protected Deck deck;
    protected Hand hand;
    // protected Board board;
    protected int health;
    protected Power powers;
    protected GameChannel channel;

    public Player(String name, int health, GameChannel channel) {
        this.name = name;
        this.deck = new Deck(channel, name);
        this.hand = new Hand(channel, name);
        // this.board = new Board();
        this.health = health;
        this.powers = new Power(channel, name);
        this.channel = channel;
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }

    public void onEvent(BaseEvent e){
        //
    }
}
