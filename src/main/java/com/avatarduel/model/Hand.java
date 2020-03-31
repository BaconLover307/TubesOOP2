package com.avatarduel.model;
import java.util.ArrayList;
import com.avatarduel.model.cards.Card;

public class Hand extends CardCollection {

	public Hand(){
        super();
    }

    public int findCard(String name){
        int i = 0;

        while ((i < this.getSize()) && (!this.contents.get(i).getName().equals(name))){
            i++;
        }
        if (this.contents.get(i).getName().equals(name)){
            return i;
        } else {
            // return null;
            return -999;
        }
    }

	public Card useCard(String name){
        int index = findCard(name);
        
        //if(index == null)
        if(index == -999){
            return null;

        } else {
            Card C = this.contents.get(index); // you meant index CMIIW
            this.contents.remove(C);
            return C;
        }
    }
}