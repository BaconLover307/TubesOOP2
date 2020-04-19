package com.avatarduel.model.cards.cardcollection;

import java.util.ArrayList;

import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.Aura;
import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
<<<<<<< HEAD
import com.avatarduel.model.gameplay.events.DestroyCharacterEvent;
=======
>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf
import com.avatarduel.model.gameplay.events.DiscardSkillEvent;
import com.avatarduel.model.gameplay.events.SummonCharacterEvent;
import com.avatarduel.model.gameplay.events.SummonSkillEvent;
import com.avatarduel.model.gameplay.events.SkillCardAttachedEvent;

public class Board implements Subscriber, Publisher,
        SummonCharacterEvent.SummonCharacterEventHandler,
        SummonSkillEvent.SummonSkillEventHandler,
        DiscardSkillEvent.DiscardSkillEventHandler,
        DestroyCharacterEvent.DestroyCharacterEventHandler
    {

<<<<<<< HEAD
    private static final int SIZE = 6;
=======
public class Board implements Subscriber, Publisher,
    SummonCharacterEvent.SummonCharacterEventHandler, SummonSkillEvent.SummonSkillEventHandler,
    DiscardSkillEvent.DiscardSkillEventHandler {
>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf
    
    private SummonedCharacter[] charBoard;
    private Skill[] skillBoard;
    private GameplayChannel channel;
    private String owner;

    public Board(GameplayChannel channel, String owner) {
        this.channel = channel;
        this.owner = owner;
        charBoard = new SummonedCharacter[SIZE];
        skillBoard = new Skill[SIZE];
        for (int i=0; i<SIZE; i++) {charBoard[i] = null;}
        for (int j=0; j<SIZE; j++) {skillBoard[j] = null;}
        channel.addSubscriber("SUMMON_CHARACTER",this);
        channel.addSubscriber("SUMMON_SKILL",this);
        channel.addSubscriber("DISCARD_SKILL",this);
        channel.addSubscriber("DESTROY_CHARACTER_EVENT",this);
    }

    public String getOwner() {return this.owner;}

    public void addChartoBoard(int id, SummonedCharacter C) {this.charBoard[id] = C;}
    public SummonedCharacter getCharwithId(int id) {return this.charBoard[id];}

    public void addSkilltoBoard(int id, Skill s) {
        this.skillBoard[id] = s;
    }
    public Skill getSkillwithId(int id) {return this.skillBoard[id];}

    public boolean[] getAvailableCharSlot() {
        boolean[] id = new boolean[SIZE];
        for (int i = 0; i<SIZE; i++ ) {
            if (this.charBoard[i] ==null) id[i] = true;
            else id[i] = false;
        }
        return id;
    }

    public boolean isCharSlotAvailable() {
        int i = 0;
        int count = 0;
        boolean[] bool = getAvailableCharSlot();
        while (i<SIZE) {
            if (!bool[i]) {
                count = count + 1;
            }
            i++;
        }
        return count<SIZE;
    }

    public boolean isCharSlotEmpty() {
        int i = 0;
        boolean[] bool = getAvailableCharSlot();
        while (i<SIZE) {
            if (!bool[i]) return false;
            i++;
        }
        return true;
    }

    public boolean[] getAvailableSkillSlot() {
        boolean[] id = new boolean[SIZE];
        for (int i = 0; i<SIZE; i++ ) {
            if (this.skillBoard[i] ==null) id[i] = true;
            else id[i] = false;
        }
        return id;
    }

    public boolean isSkillSlotAvailable() {
        int i = 0;
        boolean[] bool = getAvailableSkillSlot();
        while (!bool[i] && i<SIZE) {
            i++;
        }
        return bool[i];
    }
    
    // 1. Harus ada tempat kosong
    // 2. Harus ada karakter yg ditarget
    // TODO s arahin ke target 

<<<<<<< HEAD
    @Override
    public void publish(String topic, BaseEvent event) {
        this.channel.sendEvent(topic, event);
    }

=======
>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf
    @Override
    public void onEvent(BaseEvent e){
        if (e instanceof SummonCharacterEvent){
            this.onSummonCharacterEvent((SummonCharacterEvent) e);
        }
        else if (e instanceof SummonSkillEvent) {
            this.onSummonSkillEvent((SummonSkillEvent) e);
        }
        else if (e instanceof DiscardSkillEvent) {
            this.onDiscardSkillEvent((DiscardSkillEvent) e);
        }
        else if (e instanceof DestroyCharacterEvent) {
            this.onDestroyCharacterEvent((DestroyCharacterEvent) e);
        }
    }
    
    @Override
    public void onSummonCharacterEvent(SummonCharacterEvent e) {
        if (this.getOwner().equals(e.owner)) {
            SummonedCharacter SC = new SummonedCharacter(e.C, true, channel.activePlayer.getName(), channel);
            addChartoBoard(e.id, SC);
        }
    }

    @Override
    public void onSummonSkillEvent(SummonSkillEvent e) {
<<<<<<< HEAD
        if (this.getOwner().equals(e.owner)) {
            addSkilltoBoard(e.Sid, e.S);
        }
=======
        // TODO Masukin e.S ke map skill 
        // targetin skill ke summoned char (last clicked) pakai SkillCardAttachedEvent
        this.publish("ATTACH_SKILL", new SkillCardAttachedEvent(e.S,channel.lastClickedCard));
>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf
    }

    @Override
    public void onDiscardSkillEvent(DiscardSkillEvent e) {
        // TODO remove skill dari skill board
        if (e.S instanceof Aura) {
            Aura aura = (Aura) e.S;
            for (int i=0;i<6;i++) {
                if (charBoard[i].equals(e.SC)) {
                    charBoard[i].getCharCard().setAttack(e.SC.getCharCard().getAttack() - aura.getAttVal());
                    charBoard[i].getCharCard().setAttack(e.SC.getCharCard().getDefense() - aura.getDefVal());
                    break;
                }
            }
        }
        e.SC.getAttachedSkill().remove(e.S); 
    }
<<<<<<< HEAD
=======

    @Override
    public void publish(String topic, BaseEvent event) {
        this.channel.sendEvent(topic, event);
    }
}
>>>>>>> 049247d754f04e88e961d8755b36abe8a7b461cf

    @Override
    public void onDestroyCharacterEvent(DestroyCharacterEvent e) {
        // remove e.SC dari array summonedchar board
        if (this.owner.equals(e.SC.getOwner())) {
            if (charBoard[e.id].equals(e.SC)) {
                charBoard[e.id] = null;
            }
        }
    }

}
