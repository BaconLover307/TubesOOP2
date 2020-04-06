package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class CardCollection extends ArrayList<Card> {

    //protected ArrayList<Card> contents;
    protected GameChannel channel;
    protected String player;

	public CardCollection(GameChannel channel, String player){
        //this = new ArrayList<Card>();
        super();
        this.channel = channel;
        this.player = player;
    }

    public Card getCard(int i){
        return this.get(i);
    }

    public void addCard(Card C){
        // Adds one instance of C to collection 
        this.add(C);
    }
    
    public void removeCard(Card C){
        this.remove(C);
    }
    
    public int getSize(){
        return this.size();
    }
    
    public boolean isEmpty(){
        return (this.getSize() == 0);
    }

    public String getPlayer(){
        return this.player;
    }
    
}