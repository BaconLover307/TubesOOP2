package com.avatarduel.model.cards;
import com.avatarduel.model.Element;

public abstract class SkillCard extends Card {

    // atribut
    int powVal;

    //method
    public SkillCard(String name, Element element, String description, int power) {
        super(name,element,description);
        this.powVal = power;
    }
    
    public abstract void useSkill();
}