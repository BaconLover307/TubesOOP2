package com.avatarduel.model.cards.cardcollection;
import java.util.ArrayList;
import java.util.Random;

import com.avatarduel.model.cards.card.Card;

public class Deck extends CardCollection {

	public Deck(){
        super();
    }

	public void shuffle(){
        Random r = new Random();
        int randomNumber;
        Card cardTemp;

        ArrayList<Card> stackTemp = new ArrayList<>();
        randomNumber = r.nextInt(2*this.size()/3+1)+this.size()/3;
        for (int i = 0; i <= randomNumber; i++) {
            cardTemp = this.get(0);
            this.remove(cardTemp);
            stackTemp.add(cardTemp);
        }

        while (stackTemp.size()>0){
            randomNumber = r.nextInt(2*stackTemp.size()/3+1)+stackTemp.size()/3;
            int sizze = stackTemp.size();
            for (int i = randomNumber; i < sizze; i++) {
                cardTemp = stackTemp.get(randomNumber);
                stackTemp.remove(cardTemp);
                this.add(cardTemp);
            }
        }
    }

	public void addCard(Card C, int num){
        /* Adds several instances of C to collection */
        for (int i = 0; i < num; i++){
            this.add(C);
        }
    }

	public Card drawCard(){
        if(this.isEmpty()){
            //lose
            return null;
        } else {
            Card C = this.get(this.size() - 1);
            this.remove(C);
            return C;
        }
    }
}