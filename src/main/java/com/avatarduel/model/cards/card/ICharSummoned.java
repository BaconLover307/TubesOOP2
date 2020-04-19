package com.avatarduel.model.cards.card;

/**
 * ICharSummoned merupakan interface kartu bertipe Character yang disummon
 * @author Hengky - 13518048
 */
public interface ICharSummoned {

    /**
     * Mengubah posisi karakter dari menyerang jadi bertahan, atau sebaliknya
     */
    public void rotate();

    /**
     * bila dalam posisi menyerang, mengembalikan attack, bila dalam posisi bertahan, mengembalikan defense
     * @return nilai attack/defense
     */
    public int getPositionValue();

    /**
     * Melakukan attack ke kartu karakter lain
     * @param target kartu karakter lain
     */
    public void doAttack(SummonedCharacter target);

    /**
     * Menghancurkan kartu karakter yang di summon
     */
    public void doDestroy();

}