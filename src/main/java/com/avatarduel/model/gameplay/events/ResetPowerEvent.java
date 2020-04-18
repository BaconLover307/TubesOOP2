package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;

// TODO implementasi
public class ResetPowerEvent implements BaseEvent {

    public String player;

    public ResetPowerEvent(String player) {this.player = player;}

    public interface ResetPowerEventHandler {
        void onResetPowerEvent(ResetPowerEvent e);
    }

    public void execute(){
        System.out.println("Reset Power!");
    }
}