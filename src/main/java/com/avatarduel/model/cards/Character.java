package com.avatarduel.model.cards;
import com.avatarduel.model.Element;

public class Character extends Card {

    // atribut 
    private int attVal;
    private int defVal;
    private int powVal;

    // konstruktor
    public Character(String name, Element element, String description, String imagePath, int attack, int defense, int power){
        super(name,element,description,imagePath);
        this.attVal = attack;
        this.defVal = defense;
        this.powVal = power;
    }

    //getter dan setter
    public int getAttack() {return this.attVal;}
    public int getDefense() {return this.defVal;}
    public int getPower() {return this.powVal;}
    public void setAttack(int value) {this.attVal = this.attVal + value;}
    public void setDefense(int value) {this.defVal = this.defVal + value;}
}