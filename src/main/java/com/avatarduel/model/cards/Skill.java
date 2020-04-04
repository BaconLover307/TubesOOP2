package com.avatarduel.model.cards;
import com.avatarduel.model.Element;

public abstract class Skill extends Card {

    // atribut
    protected int powVal;

    //konstruktor
    public Skill(String name, Element element, String description, String imagePath, int power) {
        super(name,element,description,imagePath);
        this.powVal = power;
    }

    // getter
    public int getPowVal() {return this.powVal;}
    
    //public abstract void useSkill(); - kelas ini hanya blueprint kartu
}