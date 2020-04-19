package com.avatarduel.model.cards.cardcollection;

import java.util.ArrayList;

import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.Aura;
import com.avatarduel.model.cards.card.SummonedSkill;
import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.DestroyCharacterEvent;
import com.avatarduel.model.gameplay.events.DiscardSkillEvent;
import com.avatarduel.model.gameplay.events.RepositionCharacterEvent;
import com.avatarduel.model.gameplay.events.SummonCharacterEvent;
import com.avatarduel.model.gameplay.events.SummonSkillEvent;
import com.avatarduel.model.gameplay.events.SkillCardAttachedEvent;

public class Board implements Subscriber, Publisher,
        SummonCharacterEvent.SummonCharacterEventHandler,
        SummonSkillEvent.SummonSkillEventHandler,
        DiscardSkillEvent.DiscardSkillEventHandler,
        DestroyCharacterEvent.DestroyCharacterEventHandler
    {

    private static final int SIZE = 6;
    
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

    @Override
    public void publish(String topic, BaseEvent event) {
        this.channel.sendEvent(topic, event);
    }

    @Override
    public void onEvent(BaseEvent e){
        if (e.getClass() == SummonCharacterEvent.class){
            this.onSummonCharacterEvent((SummonCharacterEvent) e);
        }
        else if (e.getClass() == SummonSkillEvent.class) {
            this.onSummonSkillEvent((SummonSkillEvent) e);
        }
        else if (e.getClass() == DiscardSkillEvent.class) {
            this.onDiscardSkillEvent((DiscardSkillEvent) e);
        }
        else if (e.getClass() == DestroyCharacterEvent.class) {
            this.onDestroyCharacterEvent((DestroyCharacterEvent) e);
        }
    }
    
    @Override
    public void onSummonCharacterEvent(SummonCharacterEvent e) {
        if (this.getOwner() == e.owner) {
            SummonedCharacter SC = new SummonedCharacter(e.C, true, channel.activePlayer.getName(), channel);
            addChartoBoard(e.id, SC);
        }
    }

    @Override
    public void onSummonSkillEvent(SummonSkillEvent e) {
        if (this.getOwner() == e.owner) {
            addSkilltoBoard(e.Sid, e.S);
        }
        // TODO Masukin e.S ke array skill 
        // targetin skill ke summoned char (last clicked) pakai SkillCardAttachedEvent
//        this.publish("ATTACH_SKILL", new SkillCardAttachedEvent(e.S,channel.lastClickedCard));
    }

    @Override
    public void onDiscardSkillEvent(DiscardSkillEvent e) {
        // TODO remove skill dari skill board
        if (e.S.getClass() == Aura.class) {
            e.S = (Aura) e.S;
            //e.SC.getCharCard().setAttack(-1*(e.S.getAttVal()));
            //e.SC.getCharCard().setDefense(-1*(e.S.getDefVal()));
        }
        e.SC.getAttachedSkill().remove(e.S); 
    }

    @Override
    public void onDestroyCharacterEvent(DestroyCharacterEvent e) {
        // remove e.SC dari array summonedchar board
        for (int i=0;i<6;i++) {
            if (charBoard[i] == e.SC) {
                charBoard[i] = null;
                break;
            }
        }
    }

}


//     public static void run(String[] hojun, String[] qila) {
//         Map<String, Integer> h = new HashMap<String, Integer>();
//         for (String a : hojun) {
//             Integer freq = h.get(a);
//             h.put(a, (freq == null) ? 1 : freq++);
//         }

//         for (String a : qila) {
//             Integer freq = h.get(a);
//             h.put(a, (freq == null) ? 1 : freq--);
//         }

//         for (Map.Entry<String, Integer> a : h.entrySet()) {
//             if (a.getValue()>0) {
//                 for (int i =1; i <= a.getValue(); i++) {
//                     System.out.println(a.getKey());
//                 }
//             }
//         }
//     }
// }
