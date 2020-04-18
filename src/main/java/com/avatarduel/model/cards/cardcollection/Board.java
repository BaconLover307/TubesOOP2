package com.avatarduel.model.cards.cardcollection;

import java.util.ArrayList;

import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Skill;
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
    SummonCharacterEvent.SummonCharacterEventHandler, SummonSkillEvent.SummonSkillEventHandler,
    DiscardSkillEvent.DiscardSkillEventHandler, DestroyCharacterEvent.DestroyCharacterEventHandler,
    RepositionCharacterEvent.RepositionCharacterEventHandler {
    
    private SummonedCharacter[] charBoard;
    private Skill[] skillBoard;
    private GameplayChannel channel;
    private String owner;

    public Board(GameplayChannel channel, String owner) {
        this.channel = channel;
        this.owner = owner;
        charBoard = new SummonedCharacter[6];
        skillBoard = new Skill[6];
        for (int i=0;i<6;i++) {charBoard[i] = null;}
        for (int j=0;j<6;j++) {skillBoard[j] = null;}
        channel.addSubscriber("SUMMON_CHARACTER",this);
        channel.addSubscriber("SUMMON_SKILL",this);
        channel.addSubscriber("DISCARD_SKILL",this);
        channel.addSubscriber("DESTROY_CHARACTER_EVENT",this);
    }

    public void addChartoBoard(int id, SummonedCharacter C) {this.charBoard[id] = C;}
    public SummonedCharacter getCharwithId(int id) {return this.charBoard[id];}

    public void addSkilltoBoard(int id, Skill s, SummonedCharacter target) {this.skillBoard[id] = s;}
    public Skill getSkillwithId(int id) {return this.skillBoard[id];}
    
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
        else if (e.getClass() == RepositionCharacterEvent.class) {
            this.onRepositionCharacterEvent((RepositionCharacterEvent) e);
        }
    }
    
    @Override
    public void onSummonCharacterEvent(SummonCharacterEvent e) {
        SummonedCharacter C = new SummonedCharacter(e.C, true, channel.activePlayer, channel);
        // TODO Masukin C ke array SummonedChar
    }

    @Override
    public void onSummonSkillEvent(SummonSkillEvent e) {
        // TODO Masukin e.S ke array skill 
        // targetin skill ke summoned char (last clicked) pakai SkillCardAttachedEvent
        this.publish("ATTACH_SKILL", new SkillCardAttachedEvent(e.S,channel.lastClickedCard));
    }

    @Override
    public void onDiscardSkillEvent(DiscardSkillEvent e) {
        // TODO remove skill dari skill board
        e.SC.getAttachedSkill().remove(e.S); 
    }

    @Override
    public void onDestroyCharacterEvent(DestroyCharacterEvent e) {
        // TODO remove e.SC dari array summonedchar board
    }

    @Override
    public void onRepositionCharacterEvent(RepositionCharacterEvent e) {
        // TODO rotate tampilan e.SC
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
