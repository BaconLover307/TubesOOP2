package com.avatarduel.model.cards.cardcollection;

import com.avatarduel.model.Element;
import com.avatarduel.model.cards.card.Land;
import com.avatarduel.model.gameplay.GameplayChannel;

public class LandCollection extends CardCollection {

    private int waterPower;
    private int firePower;
    private int airPower;
    private int earthPower;
    private int energyPower;

	public LandCollection(GameplayChannel channel, String player) {
        super(channel, player);
        this.waterPower = 0;
        this.firePower = 0;
        this.airPower = 0;
        this.earthPower = 0;
        this.energyPower = 0;
    }

    public int getWaterPower(){ return this.waterPower; }
    public int getFirePower(){ return this.firePower; }
    public int getAirPower(){ return this.airPower; }
    public int getEarthPower(){ return this.earthPower; }
    public int getEnergyPower(){ return this.energyPower; }
    public void setWaterPower(int power){ this.waterPower = power; }
    public void setFirePower(int power){ this.firePower = power; }
    public void setAirPower(int power){ this.airPower = power; }
    public void setEarthPower(int power){ this.earthPower = power; }
    public void setEnergyPower(int power){ this.energyPower = power; }

    public int getLands(Element element){
        int lands = 0;

        for (int i = 0; i < this.size(); i++){
            if (this.getCard(i).getElement() == element){
                lands++;
            }
        }
        
        return lands;
    }

    public void addLand(Land L){
        this.addCard(L);
    }

    public void removeLand(Land L) {
        this.removeCard(L);
    }

    public void onNewTurn(){
        this.setWaterPower(getLands(Element.WATER));
        this.setFirePower(getLands(Element.FIRE));
        this.setAirPower(getLands(Element.AIR));
        this.setEarthPower(getLands(Element.EARTH));
        this.setEnergyPower(getLands(Element.ENERGY));
    }

    public void displayPower(Element elm) {
        switch (elm) {
            case WATER:
                System.out.println(this.getWaterPower() + "/" + this.getLands(Element.WATER));
                break;
            case FIRE:
                System.out.println(this.getFirePower() + "/" + this.getLands(Element.FIRE));
                break;
            case AIR:
                System.out.println(this.getAirPower() + "/" + this.getLands(Element.AIR));
                break;
            case EARTH:
                System.out.println(this.getEarthPower() + "/" + this.getLands(Element.EARTH));
                break;
            case ENERGY:
                System.out.println(this.getEnergyPower() + "/" + this.getLands(Element.ENERGY));
                break;
            default:
                break;
        }
    }

    public boolean spendPower(Element element, int n){
        if (element == Element.WATER){
            if (n > this.getWaterPower()){
                return false;
            } else {
                this.waterPower -= n;
                return true;
            }
        } else if (element == Element.FIRE){
            if (n > this.getFirePower()){
                return false;
            } else {
                this.firePower -= n;
                return true;
            }
        } else if (element == Element.AIR){
            if (n > this.getAirPower()){
                return false;
            } else {
                this.airPower -= n;
                return true;
            }
        } else if (element == Element.EARTH){
            if (n > this.getEarthPower()){
                return false;
            } else {
                this.earthPower -= n;
                return true;
            }
        } else {
            if (n > this.getEnergyPower()){
                return false;
            } else {
                this.energyPower -= n;
                return true;
            }
        }
    }
}