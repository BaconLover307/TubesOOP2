package com.avatarduel.view;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.cards.card.Flippable;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.DrawEvent;
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
        this.handBox = new HBox(10);
        handBox.setAlignment(Pos.CENTER);
    }

    // To flip cards
    public void flipOpen() {
        this.showHand = true;
    }
    public void flipClose() {
        this.showHand = false;
    }

    public void addCard(Card c) {
        FXMLLoader loader = new FXMLLoader(MainPageController.class.getResource("/../"));



    }

    public void onDrawEvent(DrawEvent event) {

    }

    @Override
    public void publish(String topic, BaseEvent event) {this.channel.sendEvent(topic, event);}

    @Override
    public void onEvent(BaseEvent event) {

    }
}
