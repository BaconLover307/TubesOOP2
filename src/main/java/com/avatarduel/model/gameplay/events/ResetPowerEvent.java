package com.avatarduel.model.gameplay.events;
import com.avatarduel.model.gameplay.BaseEvent;

// TODO implementasi
public class ResetPowerEvent implements BaseEvent {

    public ResetPowerEvent(){}

    public interface ReserPowerEventHandler {
        void onResetPower(ResetPowerEvent e);
    }

    public void execute(){
        System.out.println("Reset Power!");
    }
}