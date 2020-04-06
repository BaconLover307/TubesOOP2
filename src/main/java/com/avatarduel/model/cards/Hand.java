package com.avatarduel.model.cards;

public class Hand extends CardCollection {

	public Hand(){
        super();
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
}