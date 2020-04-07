package com.avatarduel.model.cards.card;
import com.avatarduel.model.Element;

public class Aura extends Skill {

    private int attVal;
    private int defVal;

    //konstruktor
    public Aura(String name, Element element,String description, String imagePath, int power, int attack, int defense) {
        super(name,element,description,imagePath,power);
        this.attVal = attack;
        this.defVal = defense;
    }

    //getter
    public int getAttVal() {return this.attVal;}
    public int getDefVal() {return this.defVal;}
}