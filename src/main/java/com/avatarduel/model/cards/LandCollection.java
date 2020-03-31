package com.avatarduel.model.cards;
import com.avatarduel.model.Element;
import java.util.ArrayList;

public class LandCollection extends CardCollection {

    private int waterPower;
    private int firePower;
    private int airPower;
    private int earthPower;

	public LandCollection(){
        super();
        this.waterPower = 0;
        this.firePower = 0;
        this.airPower = 0;
        this.earthPower = 0;
    }

    public int getWaterPower(){ return this.waterPower; }
    public int getFirePower(){ return this.firePower; }
    public int getAirPower(){ return this.airPower; }
    public int getEarthPower(){ return this.earthPower; }

    public int getActiveLands(Element element){
        int power = 0;

        for (int i = 0; i < this.getSize(); i++){
            if ((this.getCard(i).getElement() == element) && (!this.getCard(i).isTapped())){
                power++;
            }
        }
        
        return power;
    }

    public void addLand(Land L){
        if (Land.getElement() == WATER){
            this.waterPower++;
        } else if (Land.getElement() == FIRE){
            this.firePower++;
        } else if (Land.getElement() == AIR){
            this.airPower++;
        } else if (Land.getElement() == EARTH){
            this.earthPower++;
        }
        this.addCard(L);
    }

    public void displayPower(){
        System.out.println("WATER: " + this.getActiveLands(WATER) + "/" + this.getWaterPower());
        System.out.println("FIRE: " + this.getActiveLands(FIRE) + "/" + this.getFirePower());
        System.out.println("AIR: " + this.getActiveLands(AIR) + "/" + this.getAirPower());
        System.out.println("EARTH: " + this.getActiveLands(EARTH) + "/" + this.getEarthPower());
    }
}