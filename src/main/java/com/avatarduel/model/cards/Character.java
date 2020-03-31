package com.avatarduel.model.cards;
import com.avatarduel.model.Element;

public class Character extends Card {

    // atribut 
    private int attVal;
    private int defVal;
    private int powVal;

    // method
    public Character(String name, Element element, String description, int attack, int defense, int power){
        super(name,element,description);
        this.attVal = attack;
        this.defVal = defense;
        this.powVal = power;
    }
    public int getAttack() {return this.attVal;}
    public int getDefense() {return this.defVal;}
    public int getPower() {return this.powVal;}
    public int setAttack(int value) {return this.attVal + value;}
    public int setDefense(int value) {return this.defVal + value;}
}