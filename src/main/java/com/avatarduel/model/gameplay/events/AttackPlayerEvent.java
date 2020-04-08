package com.avatarduel.model.gameplay.events;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.Element;
import com.avatarduel.model.cards.card.SummonedCharacter;

import java.util.ArrayList;

public class AttackPlayerEvent implements BaseEvent {

    public int amount;
    public Player attacker;

    public AttackPlayerEvent(int amount, Player attacker){
        this.amount = amount;
        this.attacker = attacker;
    }

    public interface AttackPlayerEventHandler {
        void onAttackPlayer(AttackPlayerEvent e);
    }
    
    public void execute(){

    }
}
