package com.avatarduel.model.player;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.events.AttackPlayerEvent;
import com.avatarduel.model.gameplay.events.EndGameEvent;
import com.avatarduel.model.gameplay.events.ResetPowerEvent;
import com.avatarduel.model.gameplay.events.UseLandEvent;
import com.avatarduel.model.gameplay.events.SpendPowerEvent;
import com.avatarduel.model.cards.cardcollection.Deck;
import com.avatarduel.model.cards.cardcollection.Hand;
import com.avatarduel.model.cards.cardcollection.Board;

/**
 * Kelas untuk Player
 * @author Hengky - 13518048
 */
public class Player implements Publisher, Subscriber,
    AttackPlayerEvent.AttackPlayerEventHandler, ResetPowerEvent.ResetPowerEventHandler,
    UseLandEvent.UseLandEventHandler, SpendPowerEvent.SpendPowerEventHandler {

    protected String name;
    protected Deck deck;
    protected Hand hand;
    protected Board board;
    protected int health;
    protected Power powers;
    protected GameplayChannel channel;

    /**
     * Membuat player baru 
     * @param name nama player
     * @param health health point awal
     * @param channel gameplay channel
     */
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
        channel.addSubscriber("USE_LAND", this);
        channel.addSubscriber("SPEND_POWER_EVENT", this);
    }

    public String getName() {return this.name;}
    public Deck getDeck() {return this.deck;}
    public Hand getHand() {return this.hand;}
    public Board getBoard() {return this.board;}
    public int getHealth() {return this.health;}
    public Power getPowers() {return this.powers;}

    /**
     * Implemen interface publisher
     * @param topic 
     * @param event 
     */
    public void publish(String topic, BaseEvent event) {
        this.channel.sendEvent(topic, event);
    }

    /**
     * Implemen interface subscriber
     * @param e event
     */
    @Override
    public void onEvent(BaseEvent e){
        if (e instanceof AttackPlayerEvent){
            this.onAttackPlayer((AttackPlayerEvent) e);
        } 
        else if (e instanceof ResetPowerEvent){
            this.onResetPowerEvent((ResetPowerEvent) e);
        }
        else if (e instanceof SpendPowerEvent){
            this.onSpendPowerEvent((SpendPowerEvent) e);
        }
        else if (e instanceof UseLandEvent){
            this.onUseLandEvent((UseLandEvent) e);
        }    
    }

<<<<<<< HEAD
    /**
     * Implemen handler event attack player
     * @param e event
     */
=======
>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf
    @Override
    public void onAttackPlayer(AttackPlayerEvent e) {
        if(this.name.equals(e.target)){
            this.health -= e.amount;
            System.out.println(this.getName() + this.getHealth());
            if (this.health <= 0){
                this.publish("END_GAME", new EndGameEvent(this.name));
            }
        }
    }

    /**
     * Implemen handler event reset power
     * @param e event
     */
    @Override
    public void onResetPowerEvent(ResetPowerEvent e) {
        if (e.player.equals(this.name)){
            this.powers.resetAllPower();
            e.execute();
        }
    }

    /**
     * Implemen handler event use land
     * @param e event
     */
    @Override
    public void onUseLandEvent(UseLandEvent e){
        if (e.owner.equals(this.name)){
            this.powers.addCapacity(e.land.getElement(), 1);
            this.powers.addSize(e.land.getElement(), 1);
            e.execute();
        }
    }

    /**
     * Implemen handler event spend power
     * @param e event
     */
    @Override
    public void onSpendPowerEvent(SpendPowerEvent e) {
        if (e.sender.equals(this.name)){
            this.powers.usePower(e.element, e.amount);
        }    
    }

}
