package com.avatarduel.view;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.cards.card.Flippable;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.DrawEvent;
import com.avatarduel.view.cards.CardDisplay;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import com.avatarduel.model.cards.cardcollection.Hand;

public class HandDisplay implements Initializable, Flippable, Publisher, Subscriber {
    private Hand hand;
    private GameplayChannel channel;
    private boolean showHand;
    private double handW,handH,handX,handY;
    private HBox handBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public HandDisplay(GameplayChannel gameplayChannel, Hand hand) {
//        this.handW = handW; this.handH = handH; this.handX = handX; this.handY = handY;
        this.showHand = false;
        this.channel = gameplayChannel;
        this.channel.addSubscriber("DRAW_EVENT", this);

        this.hand = hand;
        this.handBox = new HBox(10);
        handBox.setAlignment(Pos.TOP_CENTER);
    }

    public HBox getHandBox() {return handBox;}
    public Hand getHand() {return hand;}

    // To flip cards
    public void flipOpen() {
        this.showHand = true;
    }
    public void flipClose() {
        this.showHand = false;
    }

    public void addCard(Card card) {
        try {
            FXMLLoader loader = new FXMLLoader(MainPageController.class.getResource("../fxml/CardDisplay.fxml"));
//            CardDisplay cD = ;
            loader.setControllerFactory(c -> new CardDisplay(this.channel, card));
            System.out.println("PASS1");
            System.out.println(card.getName());
//            System.out.println(cD.card_name.getText());
            System.out.println("PASS2");
//            cD.box.setPrefHeight(112.0);
//            cD.box.setPrefWidth(80.0);
            this.handBox.getChildren().add(loader.load());
        } catch (Exception e) {
            System.out.println("Hand failed to add card!");
            System.out.println("Error = " + e);
        }
    }

    public void onDrawEvent(DrawEvent event) {
        this.addCard(event.c);
    }

    @Override
    public void publish(String topic, BaseEvent event) {this.channel.sendEvent(topic, event);}

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof DrawEvent) {
            onDrawEvent((DrawEvent) event);
        }
    }
}
