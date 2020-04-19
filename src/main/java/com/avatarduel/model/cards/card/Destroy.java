package com.avatarduel.model.cards.card;
import com.avatarduel.model.Element;

/**
 * Destroy merupakan blueprint untuk skill berjenis Destroy
 * @author Hengky - 13518048
 */
public class Destroy extends Skill {

    /**
     * Membuat objek bertipe Destroy
     * @param name nama dari Destroy card
     * @param element elemen dari Destroy card
     * @param description deskripsi dari Destroy card
     * @param imagePath lokasi gambar Destroy card
     * @param power nilai power yang dibutuhkan untuk summon skill Destroy
     */
    public Destroy(String name, Element element, String description, String imagePath, int power) {
        super(name, element, description, imagePath, power);
    }
}