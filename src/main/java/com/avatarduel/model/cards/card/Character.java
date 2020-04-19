package com.avatarduel.model.cards.card;
import com.avatarduel.model.Element;

/**
 * Character adalah blueprint sebuah kartu bertipe Character
 * @author Hengky - 13518048
 */
public class Character extends Card {

    private int attVal;
    private int defVal;
    private int powVal;

    /**
     * Membuat objek Character
     * @param name nama character
     * @param element elemen character
     * @param description deskripsi karakter
     * @param imagePath lokasi gambar karakter
     * @param attack nilai attack karakter
     * @param defense nilai defense karakter
     * @param power nilai yang dibutuhkan untuk summon karakter
     */
    public Character(String name, Element element, String description, String imagePath, int attack, int defense, int power){
        super(name,element,description,imagePath);
        this.attVal = attack;
        this.defVal = defense;
        this.powVal = power;
    }

    /**
     * Mengambil nilai attack karakter
     * @return nilai attack
     */
    public int getAttack() {return this.attVal;}

    /**
     * Mengambil nilai defense karakter
     * @return nilai defense
     */
    public int getDefense() {return this.defVal;}

    /**
     * Mengambil nilai power karakter
     * @return nilai power
     */
    public int getPowVal() {return this.powVal;}

    /**
     * Menambah/Mengurangi nilai attack karakter
     * @param value nilai penambahan/pengurangan
     */
    public void setAttack(int value) {this.attVal = this.attVal + value;}

    /**
     * Menambah/Mengurangi nilai defense karakter
     * @param value nilai penambahan/pengurangan
     */
    public void setDefense(int value) {this.defVal = this.defVal + value;}
}