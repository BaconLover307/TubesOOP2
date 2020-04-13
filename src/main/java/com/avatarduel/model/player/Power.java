package com.avatarduel.model.player;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.ResetPowerEvent;
import com.avatarduel.model.gameplay.events.SpendPowerEvent;
import com.avatarduel.model.gameplay.events.UseLandEvent;
import com.avatarduel.model.Element;

public class Power implements
    Publisher, 
    Subscriber {

    private GameplayChannel channel;
    private String player;
    private ElementPower firePower;
    private ElementPower waterPower;
    private ElementPower earthPower;
    private ElementPower airPower;
    private ElementPower energyPower;
    private Element elements[] = Element.values();

    public Power(GameplayChannel channel, String player) {
        this.channel = channel;
        this.player = player;
        this.firePower = new ElementPower(Element.FIRE, 0, 0);
        this.waterPower = new ElementPower(Element.WATER, 0, 0);
        this.earthPower = new ElementPower(Element.EARTH, 0, 0);
        this.airPower = new ElementPower(Element.AIR, 0, 0);
        this.energyPower = new ElementPower(Element.ENERGY, 0, 0);
    }

    public ElementPower getPower(Element elm) {
        if (elm.equals(Element.FIRE)) return this.firePower;
        else if (elm.equals(Element.WATER)) return this.waterPower;
        else if (elm.equals(Element.EARTH)) return this.earthPower;
        else if (elm.equals(Element.AIR)) return this.airPower;
        else return this.energyPower;
    }

    public void resetAllPower() {
        for(Element e : elements) {
            getPower(e).resetPower();
        }
    }

    public void AddCapacity(Element elm, int cap) {
        getPower(elm).AddCapacity(cap);
    }

    public void UsePower(Element elm, int use) {
        //kok ini void tapi use power jadi boolean?
        getPower(elm).UsePower(use);
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }

    public void onEvent(BaseEvent e){
        if (e instanceof SpendPowerEvent){
            this.onSpendPowerEvent((SpendPowerEvent)e);
        } else if (e instanceof UseLandEvent){
            this.onUseLandEvent((UseLandEvent)e);
        }
    }

    // TODO ini seharusnya di player (?)
    public void onSpendPowerEvent(SpendPowerEvent e){
        boolean success = this.getPower(e.getElement()).UsePower(e.getVal());
        this.publish(e.getSender(), e.new Handler(success));
    }

    public void onUseLandEvent(UseLandEvent e){
        this.AddCapacity(e.getLand().getElement(), 1);
    }
}