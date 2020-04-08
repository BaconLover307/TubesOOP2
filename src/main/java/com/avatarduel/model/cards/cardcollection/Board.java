package com.avatarduel.model.cards.cardcollection;

import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.UseSkillEvent;
import com.avatarduel.model.gameplay.events.SummonCharacterEvent;
import com.avatarduel.model.gameplay.events.SummonSkillEvent;

import java.util.Map;
import java.util.HashMap;

public class Board implements Subscriber, 
    SummonCharacterEvent.SummonCharacterEventHandler, SummonSkillEvent.SummonSkillEventHandler {
    
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

    public void onUseSkillEvent(UseSkillEvent e){
        // this.addSkilltoBoard(id, e.getSkill(), target);
    }

    @Override
    public void onEvent(BaseEvent e){
        if (e.getClass() == SummonCharacterEvent.class){
            this.onSummonCharacterEvent((SummonCharacterEvent) e);
        }
        else if (e.getClass() == SummonSkillEvent.class) {
            this.onSummonSkillEvent((SummonSkillEvent) e);
        } 
    }
    
    @Override
    public void onSummonCharacterEvent(SummonCharacterEvent e) {
        // TODO Masukin e.C ke map summonedchar
    }

    @Override
    public void onSummonSkillEvent(SummonSkillEvent e) {
        // TODO Masukin e.S ke map skill
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
