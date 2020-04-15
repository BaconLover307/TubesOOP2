package com.avatarduel.model.cards.cardcollection;

import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Skill;
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

import java.util.Map;
import java.util.HashMap;

public class Board implements Subscriber, Publisher,
    SummonCharacterEvent.SummonCharacterEventHandler, SummonSkillEvent.SummonSkillEventHandler,
    DiscardSkillEvent.DiscardSkillEventHandler, DestroyCharacterEvent.DestroyCharacterEventHandler,
    RepositionCharacterEvent.RepositionCharacterEventHandler {
    
    Map<Integer, SummonedCharacter> charBoard;
    Map<Integer, Skill> skillBoard;
    private GameplayChannel channel;

    public Board(GameplayChannel channel) {
        //super();
        this.charBoard = new HashMap<Integer, SummonedCharacter>();
        this.skillBoard = new HashMap<Integer, Skill>();
        this.channel = channel;
        channel.addSubscriber("SUMMON_CHARACTER",this);
        channel.addSubscriber("SUMMON_SKILL",this);
        channel.addSubscriber("DISCARD_SKILL",this);
        channel.addSubscriber("DESTROY_CHARACTER_EVENT",this);
    }

    public void addChartoBoard(int id, SummonedCharacter C) {
        this.charBoard.put(id, C);
    }

    public SummonedCharacter getCharwithId(int id) {
        return this.charBoard.get(id);
    }

    public void addSkilltoBoard(int id, Skill s, SummonedCharacter target) {
        // 1. Harus ada tempat kosong
        // 2. Harus ada karakter yg ditarget
        this.skillBoard.put(id, s);
        // TODO s arahin ke target 
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
        // SummonedCharacter C = SummonedCharacter(e.C, boolean isAttack, String player, channel)
        // TODO Masukin C ke map summonedchar dan parameter yg blm (isAttack,player)
    }

    @Override
    public void onSummonSkillEvent(SummonSkillEvent e) {
        // TODO Masukin e.S ke map skill 
        // targetin skill ke summoned char (last clicked) pakai SkillCardAttachedEvent
        this.publish("ATTACH_SKILL", new SkillCardAttachedEvent(e.S,channel.lastClickedCard));
    }

    @Override
    public void onDiscardSkillEvent(DiscardSkillEvent e) {
        // TODO hapus skill dari summonedchar dan destroy dari board
    }

    @Override
    public void onDestroyCharacterEvent(DestroyCharacterEvent e) {
        // TODO remove e.SC dari list summonedchar board (dan skill yg di attach di removed juga)
    }

    @Override
    public void publish(String topic, BaseEvent event) {
        this.channel.sendEvent(topic, event);
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
