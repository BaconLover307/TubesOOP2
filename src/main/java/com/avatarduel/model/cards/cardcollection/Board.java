package com.avatarduel.model.cards.cardcollection;

import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.UseSkillEvent;

import java.util.Map;
import java.util.HashMap;

public class Board implements Subscriber {
    
    Map<Integer, SummonedCharacter> charBoard;
    Map<Integer, Skill> skillBoard;

    public Board() {
        super();
        this.charBoard = new HashMap<Integer, SummonedCharacter>();
        this.skillBoard = new HashMap<Integer, Skill>();
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

    public void onEvent(BaseEvent e){
        /*
        if (e instanceof UseSkillEvent){
            this.onUseSkillEvent((UseSkillEvent)e);
        }
        */
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
