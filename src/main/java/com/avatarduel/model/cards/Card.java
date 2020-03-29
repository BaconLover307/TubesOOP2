package com.avatarduel.model.cards;
import com.avatarduel.model.Element;

public class Card {

    // atributes
    private String name;
    private String description;
    private Element element;
    private boolean tapped;
    
    // method
    public Card(String name, Element element, String description){
        this.name = name;
        this.description = description;
        this.element = element;
        this.tapped = false;
    }

    public String getName() {return this.name;}
    public String getDesc() {return this.description;}
    public Element getElement() {return this.element;}
    public boolean isTapped() {return this.tapped;}
    public void setTapped() {this.tapped = true;}
}