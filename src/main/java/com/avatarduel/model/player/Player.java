package com.avatarduel.model.player;
import com.avatarduel.model.cards.Deck;
import com.avatarduel.model.cards.Hand;


public class Player {
    protected Deck deck;
    protected Hand hand;
    // protected Board board;
    protected int health;
    protected Power powers;

    public Player(int health) {
        this.deck = new Deck();
        this.hand = new Hand();
        // this.board = new Board();
        this.health = health;
        this.powers = new Power();
    }
}
