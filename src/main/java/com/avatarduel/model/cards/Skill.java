package com.avatarduel.model.cards;
import com.avatarduel.model.Element;

public abstract class Skill extends Card {

    // atribut
    protected int powVal;

    //method
    public Skill(String name, Element element, String description, int power) {
        super(name,element,description);
        this.powVal = power;
    }
    
    public abstract void useSkill();
}