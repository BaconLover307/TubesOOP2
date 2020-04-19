package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import com.avatarduel.model.player.Player;

<<<<<<< HEAD
/**
 * Kelas untuk event mengurangi power pemain sesuai elemen
 */
=======
// Sent to Power to call usePower, remembers the Element and number of Power required, remembers the sender for return address
// returns handler with boolean, true if there was enough power of the required element
// source: Hand
// target: Power
//TODO sesuaiin format
>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf
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