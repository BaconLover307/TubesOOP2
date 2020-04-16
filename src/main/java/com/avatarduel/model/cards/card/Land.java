package com.avatarduel.model.cards.card;
import com.avatarduel.model.Element;

public class Land extends Card {

    //konstruktor
    public Land(String name, Element element, String description, String imagePath){
        super(name,element,description,imagePath);
    }
    public Land(){
        super("",Element.AIR,"","");
    }
}