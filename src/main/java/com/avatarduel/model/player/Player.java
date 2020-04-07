package com.avatarduel.model.player;

import com.avatarduel.model.cards.cardcollection.Deck;
import com.avatarduel.model.cards.cardcollection.Hand;

public class Player implements IPlayer {
    protected String name;
    protected Deck deck;
    protected Hand hand;
    // protected Board board;
    protected int health;
    protected Power powers;

    public Player(String name, int health) {
        this.name = name;
        this.deck = new Deck();
        this.hand = new Hand();
        // this.board = new Board();
        this.health = health;
        this.powers = new Power();
    }

    public void onBeingAttacked(int damageValue) {
        // TODO Auto-generated method stub

    }
}
