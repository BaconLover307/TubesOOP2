package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.Phase;
import com.avatarduel.model.gameplay.BaseEvent;

public class ChangePhaseEvent implements BaseEvent {
    public Phase phase;

    public ChangePhaseEvent(Phase p) {this.phase = p;}

    public interface ChangePhaseEventHandler {
        void onChangePhase(ChangePhaseEvent e);
    }

    @Override
    public void execute() {
        System.out.println("Phase changed! Now " + phase.toString());
    }
}
