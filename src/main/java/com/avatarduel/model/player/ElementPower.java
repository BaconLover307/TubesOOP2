package com.avatarduel.model.player;

import com.avatarduel.model.Element;

public class ElementPower {
    protected Element elm;
    protected int capacity;
    protected int size;

    public ElementPower(Element elm, int capacity, int size) {
        this.elm = elm;
        this.capacity = capacity;
        this.size = size;
    }

    public void AddCapacity(int capacity){
        this.capacity += capacity;
    }

    public void AddSize(int size) {
        this.size += size;
    }

    public void ResetPower() {
        this.size = this.capacity; 
    }

    public Boolean UsePower(int use) {
        if (this.size < use) {
            return false;
        } else {
            this.size -= use;
            return true;
        }
    }
}