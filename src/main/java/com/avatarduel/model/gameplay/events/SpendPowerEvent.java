package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import com.avatarduel.model.player.Player;

/**
 * Kelas untuk event mengurangi power pemain sesuai elemen
 */
public class SpendPowerEvent implements BaseEvent {

    public String sender;
    public Element element;
    public int amount;

    /**
     * Membuat event baru
     * @param sender pemain yang dikenakan pengurangan
     * @param element element yang dipakai
     * @param amount besar element yang dipakai
     */
    public SpendPowerEvent(String sender, Element element, int amount){
        this.sender = sender;
        this.element = element;
        this.amount = amount;
    }
    
    /**
     * Handler untuk event
     */
    public interface SpendPowerEventHandler{
        public void onSpendPowerEvent(SpendPowerEvent e);
    } 

    /**
     * Mencetak pada command-line
     */
    public void execute(){
        System.out.println(sender + element + " element reduced by " + amount);
    }

}