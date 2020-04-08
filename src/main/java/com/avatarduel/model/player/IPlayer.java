package com.avatarduel.model.player;
import com.avatarduel.model.gameplay.events.AttackPlayerEvent;

public interface IPlayer {
    public void onBeingAttacked(AttackPlayerEvent e);
}