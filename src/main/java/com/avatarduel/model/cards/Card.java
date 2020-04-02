package com.avatarduel.model.cards;
import com.avatarduel.model.Element;

public class Card {

    // atributes
    protected String name;
    protected String description;
    protected Element element;
    // private boolean tapped; violate SRP
    
    // method
    public Card(String name, Element element, String description){
        this.name = name;
        this.description = description;
        this.element = element;
        //this.tapped = false;
    }

    public String getName() {return this.name;}
    public String getDesc() {return this.description;}
    public Element getElement() {return this.element;}
   // public boolean isTapped() {return this.tapped;}  // ! violate SRP
   // public void setTapped() {this.tapped = true;} // ! violate SRP
}