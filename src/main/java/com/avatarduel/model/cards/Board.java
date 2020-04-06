package com.avatarduel.model.cards;
import java.util.Map;
import java.util.HashMap;

public class Board {
    
    Map<Integer, Character> charBoard;
    Map<Integer, Skill> skillBoard;

    public Board() {
        this.charBoard = new HashMap<Integer, Character>();
        this.skillBoard = new HashMap<Integer, Skill>();
    }

    public void addChartoBoard(int id, Character C) {
        this.charBoard.put(id, C);
    }

    public Character getCharwithId(int id) {
        return this.charBoard.get(id);
    }

    public void addSkilltoBoard(int id, Skill s, Character target) {
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
