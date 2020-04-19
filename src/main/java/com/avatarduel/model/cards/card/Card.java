package com.avatarduel.model.cards.card;
import com.avatarduel.model.Element;

/**
 * Card adalah blueprint sebuah kartu
 * @author Hengky - 13518048 
 */
public class Card {

    protected String name;
    protected Element element;
    protected String description;
    protected String imagePath;
    
    /**
     * Membuat objek Card
     * @param name nama kartu
     * @param element elemen kartu
     * @param description deskripsi kartu
     * @param imagePath lokasi gambar kartu
     */
    public Card(String name, Element element, String description, String imagePath){
        this.name = name;
        this.element = element;
        this.description = description;
        this.imagePath = imagePath;
    }

    /**
     * Mengambil nama kartu
     * @return nama kartu (String)
     */
    public String getName() {return this.name;}
    
    /**
     * Mengambil deskripsi kartu
     * @return deskripsi kartu (String)
     */
    public String getDesc() {return this.description;}

    /**
     * Mengambil elemen kartu
     * @return elemen kartu (Element)
     */
    public Element getElement() {return this.element;}

    /**
     * Mengambil lokasi gambar kartu
     * @return path gambar (String)
     */
    public String getImgPath() {return this.imagePath;}
   
}