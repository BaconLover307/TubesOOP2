package com.avatarduel.model.gameplay;

public interface ICharSummoned {

    // mengubah posisi karakter dari menyerang jadi bertahan, atau sebaliknya
    public void rotate();

    // bila dalam posisi menyerang, mengembalikan attack.
    // bila dalam posisi bertahan, mengembalikan defense
    public int getPositionValue();
}