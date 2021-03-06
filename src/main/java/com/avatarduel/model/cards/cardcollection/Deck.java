package com.avatarduel.model.cards.cardcollection;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.DrawEvent;
import com.avatarduel.model.gameplay.events.EndGameEvent;
import com.avatarduel.model.gameplay.events.ResetPowerEvent;
import com.avatarduel.model.gameplay.events.ChangePhaseEvent;
import com.avatarduel.model.cards.card.Land;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Aura;
import com.avatarduel.model.cards.card.PowerUp;
import com.avatarduel.model.cards.card.Destroy;
import com.avatarduel.util.CSVReader;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import com.avatarduel.util.CSVReader;
import java.io.IOException;
import java.net.URISyntaxException;
import com.avatarduel.model.Element;
import com.avatarduel.model.Phase;
import com.avatarduel.model.cards.card.Card;
import java.lang.Math;

public class Deck extends CardCollection implements Publisher {

    public Deck(GameplayChannel channel, String player){
        super(channel, player);
    }

	public void shuffle(){
        Collections.shuffle(this); // shuffle deck each turn before draw
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
    public void loadDeck(File fileChar, File fileAura, File fileLand, File fileDestroy, File filePowerUp, int amount) throws IOException, URISyntaxException {

        CSVReader charReader = new CSVReader(fileChar, "\t");
        charReader.setSkipHeader(true);
        List<String[]> charRows = charReader.read();

        CSVReader auraReader = new CSVReader(fileAura, "\t");
        auraReader.setSkipHeader(true);
        List<String[]> auraRows = auraReader.read();

        CSVReader landReader = new CSVReader(fileLand, "\t");
        landReader.setSkipHeader(true);
        List<String[]> landRows = landReader.read();

        CSVReader destroyReader = new CSVReader(fileDestroy, "\t");
        destroyReader.setSkipHeader(true);
        List<String[]> destroyRows = destroyReader.read();

        CSVReader powerupReader = new CSVReader(filePowerUp, "\t");
        powerupReader.setSkipHeader(true);
        List<String[]> powerupRows = powerupReader.read();

        Collections.shuffle(charRows);
        int amountCharLand = (int) Math.floor((2*amount/5));
        for (int i=0; i<amountCharLand; i++)
        {
            this.addCharFromArr(charRows.get(i % charRows.size()));
        }

        Collections.shuffle(landRows);
        for (int j=0; j<amountCharLand; j++)
        {
            this.addLandFromArr(landRows.get(j % landRows.size()));
        }

        Collections.shuffle(auraRows);
        int amountAuraPU = (int) Math.floor(((amount - (2*amountCharLand))/3));
        for (int k=0; k<amountAuraPU; k++)
        {
            this.addAuraFromArr(auraRows.get(k % auraRows.size()));
        }

        Collections.shuffle(powerupRows);
        for (int l=0; l<amountAuraPU; l++)
        {
            this.addPowerUpFromArr(powerupRows.get(l % powerupRows.size()));
        }


        Collections.shuffle(destroyRows);
        int amountDestroy = amount - (2*amountCharLand) - (2*amountAuraPU);
        for (int m=0; m<amountDestroy; m++)
        {
            this.addDestroyFromArr(destroyRows.get(m % destroyRows.size()));
        }
        
        this.shuffle();
    }

	public void addCard(Card C){
        this.add(C);
    }

    public void addLandFromArr(String[] arr) {
        addCard(new Land(arr[1], Element.valueOf(arr[2]), arr[3], arr[4]));
    }

    public void addCharFromArr(String[] arr) {
        addCard(new Character(arr[1], Element.valueOf(arr[2]), arr[3], arr[4], Integer.parseInt(arr[5]), Integer.parseInt(arr[6]), Integer.parseInt(arr[7])));
    }
    
    public void addAuraFromArr(String[] arr) {
        addCard(new Aura(arr[1], Element.valueOf(arr[2]), arr[3], arr[4], Integer.parseInt(arr[5]), Integer.parseInt(arr[6]), Integer.parseInt(arr[7])));
    }

    public void addPowerUpFromArr(String[] arr) {
        addCard(new PowerUp(arr[1], Element.valueOf(arr[2]), arr[3], arr[4], Integer.parseInt(arr[5])));
    }

    public void addDestroyFromArr(String[] arr) {
        addCard(new Destroy(arr[1], Element.valueOf(arr[2]), arr[3], arr[4], Integer.parseInt(arr[5])));
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

}