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
    protected String player;

    public Player(String name, int health, GameChannel channel, String player) {
        this.name = name;
        this.deck = new Deck(channel, player);
        this.hand = new Hand(channel, player);
        // this.board = new Board();
        this.health = health;
        this.powers = new Power();
        this.channel = channel;
        this.player = player;
    }

    public void publish(String topic, BaseEvent event){
        //
    }

    public void onEvent(BaseEvent e){
        //
    }
}
