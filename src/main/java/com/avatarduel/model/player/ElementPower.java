package com.avatarduel.model.player;

import com.avatarduel.model.Element;

public class ElementPower {
    private Element elm;
    private int capacity;
    private int size;

    public ElementPower(Element elm, int capacity, int size) {
        this.elm = elm;
        this.capacity = capacity;
        this.size = size;
    }

    public int getSize() {return this.size;}

    public int getCapacity() {return this.capacity;}

    public void addCapacity(int capacity){
        this.capacity += capacity;
    }

    public void addSize(int size) {
        this.size += size;
    }

    public void resetPower() {
        this.size = this.capacity; 
    }

    public void usePower(int use) {
        this.size -= use;
    }
}