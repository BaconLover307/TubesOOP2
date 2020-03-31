package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class Hand extends CardCollection {

	public Hand(){
        super();
    }

    public int findCard(String name){
        i = 0;

        while ((i < this.getSize()) && (!this.getCard(i).getName().equals(name))){
            i++;
        }
        if (this.getCard(i).getName().equals(name)){
            return i;
        } else {
            return null;
        }
    }

	public Card useCard(String name){
        /* Diasumsi bahwa ada kartu dengan nama name dalam hand */
        /* KAMUS */
        int index = findCard(name);

        /* ALGORITMA */
        Card C = this.getCard(i);
        this.contents.remove(C);
        return C;
    }

    public void displayHand(){
        if(this.isEmpty()){
            System.out.println("Hand is Empty!");
        } else {
            for(i = 0; i < this.getSize(); i++){
                System.out.println(this.getCard(i).getName());
            }
        }
    }
}