package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class Hand extends CardCollection {

	public Hand(){
        super();
    }

    public int findCard(String name){
        int i = 0;

        while ((i < this.contents.size()) && (!this.contents.get(i).getName().equals(name))){
            i++;
        }
        if (this.contents.get(i).getName().equals(name)){
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
        Card C = this.contents.get(index);
        this.contents.remove(C);
        return C;
    }

    public void displayHand(){
        if(this.contents.isEmpty()){
            System.out.println("Hand is Empty!");
        } else {
            for(int i = 0; i < this.contents.size(); i++){
                System.out.println(this.contents.get(i).getName());
            }
        }
    }
}