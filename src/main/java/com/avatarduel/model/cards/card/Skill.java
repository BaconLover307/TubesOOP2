package com.avatarduel.model.cards.card;
import com.avatarduel.model.Element;

/**
 * Skill merupakan blueprint kartu bertipe Skill
 * @author Hengky - 13518048
 */
public class Skill extends Card {

    // atribut
    protected int powVal;

    /**
     * Membuat objek Skill baru
     * @param name nama skill
     * @param element elemen skill
     * @param description deskripsi skill
     * @param imagePath lokasi gambar skill
     * @param power nilai yang dibutuhkan untuk summon skill
     */
    public Skill(String name, Element element, String description, String imagePath, int power) {
        super(name,element,description,imagePath);
        this.powVal = power;
    }

    /**
     * Mengambil nilai power
     * @return nilai power
     */
    public int getPowVal() {return this.powVal;}
}