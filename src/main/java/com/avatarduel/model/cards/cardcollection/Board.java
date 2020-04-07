package com.avatarduel.model.cards.cardcollection;

import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.cards.card.Skill;

import java.util.Map;
import java.util.HashMap;

public class Board {
    
    Map<Integer, SummonedCharacter> charBoard;
    Map<Integer, Skill> skillBoard;

    public Board() {
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
