package com.avatarduel.model.cards;
import com.avatarduel.model.gameplay.SummonedCharacter;
import com.avatarduel.model.gameplay.SummonedSkill;
import java.util.Map;
import java.util.HashMap;

public class Board {
    
    Map<Integer, SummonedCharacter> charBoard;
    Map<Integer, SummonedSkill> skillBoard;

    public Board(GameChannel channel, String player) {
        this.charBoard = new HashMap<Integer, SummonedCharacter>();
        this.skillBoard = new HashMap<Integer, SummonedSkill>();
    }

    public void addChartoBoard(int id, Character C) {
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
