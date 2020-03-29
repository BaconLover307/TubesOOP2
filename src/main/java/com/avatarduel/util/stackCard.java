//package com.jundu.myapplication;

import java.util.ArrayList;
import java.util.Random;

public class stackCard {
    private ArrayList<card> stack;

    public stackCard(String type) {
        stack = new ArrayList<>();
        if (type.equals("deck")) {
            //TODO balikin ke yang bener
            for (int i = 1; i <= 13; i++) {
                stack.add(new card('C', i));
            }
            for (int i = 1; i <= 13; i++) {
                stack.add(new card('C', i));
            }
            for (int i = 1; i <= 13; i++) {
                stack.add(new card('C', i));
            }
            for (int i = 1; i <= 13; i++) {
                stack.add(new card('C', i));
            }
        }
    }

    public int getSize(){
        return stack.size();
    }

    public void putCard(card d){
        stack.add(d);
    }

    public card draw(){
        if(stack.size()>0) {
            card top = stack.get(stack.size() - 1);
            stack.remove(top);
            return top;
        }else{
            return null;
        }
    }

    public void perfect_riffle(){
        int len = stack.size();
        for (int i = len/2; i < len ; i++) {
            card temp = stack.get(i);
            stack.remove(temp);
            stack.add((i-len/2)*2,temp);
        }
    }
    public void shuffle(){
        Random r = new Random();
        int randomNumber;
        card cardTemp;

        ArrayList<card> stackTemp = new ArrayList<>();
        randomNumber = r.nextInt(2*stack.size()/3+1)+stack.size()/3;
        for (int i = 0; i <= randomNumber; i++) {
            cardTemp = stack.get(0);
            stack.remove(cardTemp);
            stackTemp.add(cardTemp);
        }

        while (stackTemp.size()>0){
            randomNumber = r.nextInt(2*stackTemp.size()/3+1)+stackTemp.size()/3;
            int sizze = stackTemp.size();
            for (int i = randomNumber; i < sizze; i++) {
                cardTemp = stackTemp.get(randomNumber);
                stackTemp.remove(cardTemp);
                stack.add(cardTemp);
            }
        }
    }

}
