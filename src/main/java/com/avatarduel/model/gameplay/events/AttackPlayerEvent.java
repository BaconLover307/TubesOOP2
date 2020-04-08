package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.Element;
import com.avatarduel.model.cards.card.SummonedCharacter;

import java.util.ArrayList;

public class AttackPlayerEvent implements BaseEvent {

    public int amount;
    public Player target;

    public AttackPlayerEvent(int amount, Player target){
        this.amount = amount;
        this.target = target;
    }

    public interface AttackPlayerEventHandler {
        void onAttackPlayer(AttackPlayerEvent e);
    }
    
    public void execute(){

    }
}
