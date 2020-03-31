package com.avatarduel.model.cards;
import com.avatarduel.model.Element;

public class Aura extends Skill {

    private int attVal;
    private int defVal;

    //method
    public Aura(String name, Element element,String description, int power, int attack, int defense) {
        super(name,element,description,power);
        this.attVal = attack;
        this.defVal = defense;
    }

    public void useSkill() {
        // setAttack(this.attVal);
        // setDefense(this.defVal);
    
    }
}