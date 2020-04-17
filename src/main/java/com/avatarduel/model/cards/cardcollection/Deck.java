package com.avatarduel.model.cards.cardcollection;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.DrawEvent;
import com.avatarduel.model.gameplay.events.EndGameEvent;
import com.avatarduel.model.gameplay.events.ResetPowerEvent;
import com.avatarduel.model.gameplay.events.CardClickedEvent;
import com.avatarduel.model.cards.card.Land;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Aura;
import com.avatarduel.util.CSVReader;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import com.avatarduel.util.CSVReader;
import java.io.IOException;
import java.net.URISyntaxException;
import com.avatarduel.model.Element;
import com.avatarduel.model.cards.card.Card;
import java.lang.Math;

public class Deck extends CardCollection implements
    Publisher,
    Subscriber,
    CardClickedEvent.CardClickedEventHandler {

    public Deck(GameplayChannel channel, String player){
        super(channel, player);
    }

	public void shuffle(){
        Collections.shuffle(this , new Random()); // shuffle deck each turn before draw
    /*    Random r = new Random();
        int randomNumber;
        Card cardTemp;

        ArrayList<Card> stackTemp = new ArrayList<>();
        randomNumber = r.nextInt(2*this.size()/3+1)+this.size()/3;
        for (int i = 0; i <= randomNumber; i++) {
            cardTemp = this.get(0);
            this.remove(cardTemp);
            stackTemp.add(cardTemp);
        }

        while (stackTemp.size()>0){
            randomNumber = r.nextInt(2*stackTemp.size()/3+1)+stackTemp.size()/3;
            int size = stackTemp.size();
            for (int i = randomNumber; i < size; i++) {
                cardTemp = stackTemp.get(randomNumber);
                stackTemp.remove(cardTemp);
                this.add(cardTemp);
            }
        } */
    }

    // * Untuk meload kartu" ke deck
    public void loadDeck(File fileChar, File fileAura, File fileLand, int amount) throws IOException, URISyntaxException {

        CSVReader charReader = new CSVReader(fileChar, "\t");
        charReader.setSkipHeader(true);
        List<String[]> charRows = charReader.read();

        CSVReader auraReader = new CSVReader(fileAura, "\t");
        auraReader.setSkipHeader(true);
        List<String[]> auraRows = auraReader.read();

        CSVReader landReader = new CSVReader(fileLand, "\t");
        landReader.setSkipHeader(true);
        List<String[]> landRows = landReader.read();

        Collections.shuffle(charRows, new Random());
        int amountCharLand = (int) Math.floor(2*(amount/5));
        for (int i=0; i<amountCharLand; i++)
        {
            this.addCharFromArr(charRows.get(i));
        }

        Collections.shuffle(landRows, new Random());
        for (int j=0; j<amountCharLand; j++)
        {
            this.addAuraFromArr(auraRows.get(j));
        }  

        Collections.shuffle(auraRows, new Random());
        int amountSkill = amount - 2*amountCharLand;
        for (int k=0; k<amountSkill; k++)
        {
            this.addAuraFromArr(auraRows.get(k));
        } 
        System.out.println(this.size());
    }

	public void addCard(Card C){
        this.add(C);
    }

    public void addLandFromArr(String[] arr) {
        Element elm;
        switch (arr[2]) {
            case "AIR": elm = Element.AIR; 
            case "FIRE": elm = Element.FIRE; 
            case "EARTH": elm = Element.EARTH; 
            case "WATER": elm = Element.WATER; 
            default: elm = Element.ENERGY; 
        }
        addCard(new Land(arr[1], elm, arr[3], arr[4]));
    }

    public void addCharFromArr(String[] arr) {
        Element elm;
        switch (arr[2]) {
            case "AIR": elm = Element.AIR; 
            case "FIRE": elm = Element.FIRE; 
            case "EARTH": elm = Element.EARTH; 
            case "WATER": elm = Element.WATER; 
            default: elm = Element.ENERGY; 
        }
        addCard(new Character(arr[1], elm, arr[3], arr[4], Integer.parseInt(arr[5]), Integer.parseInt(arr[6]), Integer.parseInt(arr[7])));
    }
    
    public void addAuraFromArr(String[] arr) {
        Element elm;
        switch (arr[2]) {
            case "AIR": elm = Element.AIR; 
            case "FIRE": elm = Element.FIRE; 
            case "EARTH": elm = Element.EARTH; 
            case "WATER": elm = Element.WATER; 
            default: elm = Element.ENERGY; 
        }
        addCard(new Aura(arr[1], elm, arr[3], arr[4], Integer.parseInt(arr[5]), Integer.parseInt(arr[6]), Integer.parseInt(arr[7])));
    }

    public void doDraw(){
        if(this.isEmpty()){
            this.publish("END_GAME", new EndGameEvent(this.getPlayer()));
        } else {
            Card C = this.get(this.size() - 1);
            this.remove(C);
            this.publish("DRAW_EVENT",new DrawEvent(C, this.getPlayer()));
        }
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }

    public void onEvent(BaseEvent e){
        if(e.getClass() == CardClickedEvent.class){
            this.onCardClicked((CardClickedEvent) e);
        }
    }

    @Override
    public void onCardClicked(CardClickedEvent e) {
        if(this.channel.activePlayer.equals(this.player) && this.channel.phase.equals("DRAW_PHASE")){
            this.doDraw();
            this.publish("RESET_POWER_EVENT", new ResetPowerEvent());
            this.channel.phase = "MAIN_PHASE";
        }

    }

}