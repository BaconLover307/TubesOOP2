package com.avatarduel.model.player;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.events.AttackPlayerEvent;
import com.avatarduel.model.gameplay.events.EndGameEvent;
import com.avatarduel.model.gameplay.events.ResetPowerEvent;
import com.avatarduel.model.cards.cardcollection.Deck;
import com.avatarduel.model.cards.cardcollection.Hand;
import com.avatarduel.model.cards.cardcollection.Board;

public class Player implements Publisher, Subscriber,
    AttackPlayerEvent.AttackPlayerEventHandler, ResetPowerEvent.ResetPowerEventHandler {

    protected String name;
    protected Deck deck;
    protected Hand hand;
    protected Board board;
    protected int health;
    protected Power powers;
    protected GameplayChannel channel;

    public Player(String name, int health, GameplayChannel channel) {
        this.name = name;
        this.deck = new Deck(channel, name);
        this.hand = new Hand(channel, name);
        this.board = new Board(channel, name);
        this.health = health;
        this.powers = new Power(channel, name);
        this.channel = channel;
        channel.addSubscriber("ATTACK_PLAYER_EVENT", this);
        channel.addSubscriber("RESET_POWER_EVENT", this);
    }

    public String getName() {return this.name;}
    public Deck getDeck() {return this.deck;}
    public Hand getHand() {return this.hand;}
    public Board getBoard() {return this.board;}
    public int getHealth() {return this.health;}
    public Power getPower() {return this.powers;}

    public void publish(String topic, BaseEvent event) {
        this.channel.sendEvent(topic, event);
    }

    public void onEvent(BaseEvent e){
        if (e.getClass() == AttackPlayerEvent.class){
            this.onAttackPlayer((AttackPlayerEvent) e);
        } 
        else if (e.getClass() == ResetPowerEvent.class){
            this.onResetPowerEvent((ResetPowerEvent) e);
        } 
    }


    @Override
    public void onAttackPlayer(AttackPlayerEvent e) {
        if(this.name == e.target){
            this.health -= e.amount;
            if (this.health <= 0){
                this.publish("GAMESTATE", new EndGameEvent(this.name));
            }
        }
    }

    @Override
    public void onResetPowerEvent(ResetPowerEvent e) {
        this.powers.resetAllPower();
    }

}
