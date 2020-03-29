package com.avatarduel.model.cards;
import com.avatarduel.model.Element;

public class CharacterCard extends Card {

    // atribut 
    int attVal;
    int defVal;
    int powVal;

    // method
    public CharacterCard(String name, Element element, String description, int attack, int defense, int power){
        super(name,element,description);
        this.attVal = attack;
        this.defVal = defense;
        this.powVal = power;
    }
    public int getAttack() {return this.attVal;}
    public int getDefense() {return this.defVal;}
    public int getPower() {return this.powVal;}
}