package com.avatarduel.model.player;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.events.AttackPlayerEvent;
import com.avatarduel.model.gameplay.events.EndGameEvent;
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
        this.deck = new Deck(channel, name);
        this.hand = new Hand(channel, name);
        // this.board = new Board();
        this.health = health;
        this.powers = new Power(channel, name);
        this.channel = channel;
    }

    public void damage(int damageVal){
        this.health -= damageVal;
    }

    public String getName(){
        return this.name;
    }

    public void onBeingAttacked(AttackPlayerEvent e) {
        this.damage(e.getDamage());
        if (this.health <= 0){
            this.publish("Gamestate", new EndGameEvent(this.name));
        }
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }

    public void onEvent(BaseEvent e){
        if (e instanceof AttackPlayerEvent){
            this.onBeingAttacked((AttackPlayerEvent) e);
        } 
    }
}
