package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class CardCollection {

    protected ArrayList<Card> contents;
    
	public CardCollection(){
        this.contents = new ArrayList<Card>();
    }

    public int getSize(){
        return this.contents.size();
    }

    public boolean isEmpty(){
        return (this.getSize() == 0);
    }

    public Card getCard(int i){
        return this.contents.get(i);
    }

    public void addCard(Card C){
        /* Adds one instance of C to collection */
        this.contents.add(C);
    }

    public void removeCard(Card C){
        this.contents.remove(C);
    }
}