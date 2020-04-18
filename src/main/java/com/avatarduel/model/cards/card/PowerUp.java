package com.avatarduel.model.cards.card;
import com.avatarduel.model.Element;

/**
 * PowerUp merupakan kelas blueprint skill card berjenis PowerUp
 * @author Hengky - 13518048
 */
public class PowerUp extends Skill {

    /**
     * Membuat objek PowerUp baru
     * @param name nama power up card
     * @param element elemen power up card
     * @param description deskripsi power up card
     * @param imagePath lokasi gambar power up card
     * @param power nilai yang dibutuhkan untuk summon power up card
     */
    public PowerUp(String name, Element element,String description, String imagePath, int power){
        super(name,element,description,imagePath,power);
    }
}