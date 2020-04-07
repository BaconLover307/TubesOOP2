package com.avatarduel.model.cards.card;
import com.avatarduel.model.Element;

public class Card {

    // atributes
    protected String name;
    protected Element element;
    protected String description;
    protected String imagePath;
    
    
    // konstruktor
    public Card(String name, Element element, String description, String imagePath){
        this.name = name;
        this.element = element;
        this.description = description;
        this.imagePath = imagePath;
    }

    // getter
    public String getName() {return this.name;}
    public String getDesc() {return this.description;}
    public Element getElement() {return this.element;}
    public String getImgPath() {return this.imagePath;}
   
}