package com.avatarduel.model.cards.card;
import com.avatarduel.model.Element;

/**
 * Aura merupakan kelas blueprint skill berjenis Aura
 * @author Hengky - 13518048
 */
public class Aura extends Skill {

    private int attVal;
    private int defVal;

    /**
     * Membuat objek Aura baru
     * @param name nama Aura
     * @param element elemen Aura
     * @param description deskripsi Aura
     * @param imagePath lokasi gambar Aura
     * @param power nilai yang dibutuhkan untuk summon Aura
     * @param attack nilai attack yang ditambahkan ke character
     * @param defense nilai defense yang tambahkan ke character
     */
    public Aura(String name, Element element,String description, String imagePath, int power, int attack, int defense) {
        super(name,element,description,imagePath,power);
        this.attVal = attack;
        this.defVal = defense;
    }

    /**
     * Mengambil nilai attack 
     * @return nilai attack 
     */
    public int getAttVal() {return this.attVal;}

    /**
     * Mengambil nilai defense
     * @return nilai defense
     */
    public int getDefVal() {return this.defVal;}
}