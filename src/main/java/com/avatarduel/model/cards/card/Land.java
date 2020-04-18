package com.avatarduel.model.cards.card;
import com.avatarduel.model.Element;

/**
 * Land merupakan blueprint kartu bertipe Land
 * @author Hengky - 13518048
 */
public class Land extends Card {

    /**
     * Membuat objek Land baru
     * @param name nama Land
     * @param element elemen Land
     * @param description deskripsi Land
     * @param imagePath lokasi gambar Land
     */
    public Land(String name, Element element, String description, String imagePath){
        super(name,element,description,imagePath);
    }
}