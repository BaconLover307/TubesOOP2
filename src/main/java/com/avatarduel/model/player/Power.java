package com.avatarduel.model.player;
import com.avatarduel.model.Element;
import com.avatarduel.model.events.GameChannel;
import com.avatarduel.model.events.BaseEvent;
import com.avatarduel.model.events.ResetPowerEvent;
import com.avatarduel.model.events.SpendPowerEvent;
import com.avatarduel.model.events.Publisher;
import com.avatarduel.model.events.Subscriber;

public class Power implements 
    Publisher, 
    Subscriber {
    
    private GameChannel channel;
    private String player;
    private ElementPower firePower;
    private ElementPower waterPower;
    private ElementPower earthPower;
    private ElementPower airPower;
    private Element elements[] = Element.values();

    public Power(GameChannel channel, String player) {
        this.channel = channel;
        this.player = player;
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

    public void onResetPowerEvent(ResetPowerEvent e){
        this.ResetAllPower();
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }

    public void onEvent(BaseEvent e){
        //
    }

    public void onSpendPowerEvent(SpendPowerEvent e){
        boolean success;

        if (e.getElement() == Element.FIRE) success = this.firePower.UsePower(e.getVal());
        else if (e.getElement() == Element.WATER) success = this.waterPower.UsePower(e.getVal());
        else if (e.getElement() == Element.EARTH) success = this.earthPower.UsePower(e.getVal());
        else success = this.airPower.UsePower(e.getVal());

        this.publish(e.getSender(), e.new Handler(success));
        
    }
}