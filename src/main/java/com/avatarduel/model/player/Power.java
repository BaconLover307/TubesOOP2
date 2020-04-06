package com.avatarduel.model.player;
import com.avatarduel.model.Element;

public class Power {    
    private ElementPower firePower;
    private ElementPower waterPower;
    private ElementPower earthPower;
    private ElementPower airPower;
    private Element elements[] = Element.values();

    public Power() {
        this.firePower = new ElementPower(Element.FIRE, 0, 0);
        this.waterPower = new ElementPower(Element.WATER, 0, 0);
        this.earthPower = new ElementPower(Element.EARTH, 0, 0);
        this.airPower = new ElementPower(Element.AIR, 0, 0);
    }

    public ElementPower getPower(Element elm) {
        if (elm == Element.FIRE) return this.firePower;
        else if (elm == Element.WATER) return this.waterPower;
        else if (elm == Element.EARTH) return this.earthPower;
        else return this.airPower;
    }

    public void ResetAllPower() {
        for(Element e : elements) {
            getPower(e).ResetPower();
        }
    }

    public void AddCapacity(Element elm, int cap) {
        getPower(elm).AddCapacity(cap);
    }

    public void UsePower(Element elm, int use) {
        getPower(elm).UsePower(use);
    }

}