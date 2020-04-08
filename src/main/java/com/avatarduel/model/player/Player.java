package com.avatarduel.model.player;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.events.AttackPlayerEvent;
import com.avatarduel.model.gameplay.events.EndGameEvent;
import com.avatarduel.model.cards.cardcollection.Deck;
import com.avatarduel.model.cards.cardcollection.Hand;

public class Player implements Publisher, Subscriber,
    AttackPlayerEvent.AttackPlayerEventHandler {

    protected String name;
    protected Deck deck;
    protected Hand hand;
    // protected Board board;
    protected int health;
    protected Power powers;
    protected GameplayChannel channel;

    public Player(String name, int health, GameplayChannel channel) {
        this.name = name;
        this.deck = new Deck(channel, name);
        this.hand = new Hand(channel, name);
        // this.board = new Board();
        this.health = health;
        this.powers = new Power(channel, name);
        this.channel = channel;
        channel.addSubscriber("ATTACK_PLAYER_EVENT", this);
    }

    public String getName(){
        return this.name;
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }

    public void onEvent(BaseEvent e){
        if (e.getClass() == AttackPlayerEvent.class){
            this.onAttackPlayer((AttackPlayerEvent) e);
        } 
    }

    public Deck getDeck() {return this.deck;}

    @Override
    public void onAttackPlayer(AttackPlayerEvent e) {
        if(this == e.target){
            this.health -= e.amount;
            if (this.health <= 0){
                this.publish("GAMESTATE", new EndGameEvent(this.name));
            }
        }
    }

}
