package com.avatarduel.model.cards;
import com.avatarduel.model.events;
import com.avatarduel.model.Element;
import java.util.ArrayList;
import java.util.Random;

public class Deck extends CardCollection implements
    DrawEvent,
    DrawEvent.Handler, 
    EndGameEvent, 
    Publisher,
    Subscriber {

	public Deck(GameChannel channel, String player){
        super(channel, player);
    }

	public void shuffle(){
        Random r = new Random();
        int randomNumber;
        Card cardTemp;

        ArrayList<Card> stackTemp = new ArrayList<>();
        randomNumber = r.nextInt(2*this.contents.size()/3+1)+this.contents.size()/3;
        for (int i = 0; i <= randomNumber; i++) {
            cardTemp = this.contents.get(0);
            this.contents.remove(cardTemp);
            stackTemp.add(cardTemp);
        }

        while (stackTemp.size()>0){
            randomNumber = r.nextInt(2*stackTemp.size()/3+1)+stackTemp.size()/3;
            int sizze = stackTemp.size();
            for (int i = randomNumber; i < sizze; i++) {
                cardTemp = stackTemp.get(randomNumber);
                stackTemp.remove(cardTemp);
                this.contents.add(cardTemp);
            }
        }
    }

	public void addCard(Card C, int num){
        /* Adds several instances of C to collection */
        for (int i = 0; i < num; i++){
            this.contents.add(C);
        }
    }

	public Card drawCard(){
        if(this.isEmpty()){
            //lose
            return null;
        } else {
            Card C = this.contents.get(this.contents.size() - 1);
            this.contents.remove(C);
            return C;
        }
    }

    /*
    public UseCard(Character C, String target){
        this.gc.publish(target, new SummonCharacterEvent(C));
    }

    public UseCard(Land C, String target){
        this.gc.publish(target, new UseLandEvent(C));
    }

    public UseCard(Skill C, String target){
        this.gc.publish(target, new UseSkillEvent(C));
    }
    */
    public void onDrawEvent(DrawEvent e){
        if (this.isEmpty()){
            this.gc.publish(EndGameEvent(this.getPlayer()));
        } else {
            Card drawn = this.drawCard();
            String target = this.getPlayer() + " Hand";
            this.gc.publish(target, new DrawEvent.Handler(drawn));
        }
    }
    
    public void onEvent(BaseEvent e){
        /*
        this.onDrawEvent(e);
        */
    } 
    
}