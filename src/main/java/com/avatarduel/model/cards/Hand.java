package com.avatarduel.model.cards;
import com.avatarduel.model.events;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class Hand extends CardCollection implements
    DrawEvent.Handler,
    Subscriber {

	public Hand(GameChannel channel, String player){
        super(channel, player);
    }

    public int findCard(String name){
        int i = 0;

        while ((i < this.size()) && (!this.get(i).getName().equals(name))){
            i++;
        }
        if (this.get(i).getName().equals(name)){
            return i;
        } else {
            return -999; // null can't converted to int
        }
    }

	public Card useCard(String name){
        /* Diasumsi bahwa ada kartu dengan nama name dalam hand */
        /* KAMUS */
        int index = findCard(name);

        /* ALGORITMA */
        Card C = this.get(index);
        this.remove(C);
        return C;
    }

    public void displayHand(){
        if(this.isEmpty()){
            System.out.println("Hand is Empty!");
        } else {
            for(int i = 0; i < this.size(); i++){
                System.out.println(this.get(i).getName());
            }
        }
    }

    void onDrawEvent(DrawEvent.Handler e){
        this.addCard(e.getCard());
    }

    void onEvent(BaseEvent e){
        /*
        this.onDrawEvent(e);
        */
    }
}