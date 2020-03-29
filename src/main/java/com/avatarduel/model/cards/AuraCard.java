package com.avatarduel.model.cards;
import com.avatarduel.model.Element;

public class AuraCard extends SkillCard {

    int attVal;
    int defVal;

    //method
    public AuraCard(String name, Element element,String description, int power, int attack, int defense) {
        super(name,element,description,power);
        this.attVal = attack;
        this.defVal = defense;
    }

    public void useSkill() {

    }
}