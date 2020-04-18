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
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.avatarduel.model.cards.cardcollection.Hand;

public class HandDisplay implements BaseView, Flippable, Publisher, Subscriber {

    private Hand hand;
    private ArrayList<CardDisplay> cardList;
    private GameplayChannel channel;
    private boolean showHand;
    private HBox handBox;

    public HandDisplay(GameplayChannel gameplayChannel, Hand hand) {
//        this.handW = handW; this.handH = handH; this.handX = handX; this.handY = handY;
        this.showHand = false;
        this.channel = gameplayChannel;
        this.channel.addSubscriber("DRAW_EVENT", this);

        this.hand = hand;
        this.handBox = new HBox(CARD_SIZEW * 70 / 80);
        handBox.setAlignment(Pos.TOP_CENTER);

        this.cardList = new ArrayList<CardDisplay>();
    }

    public HBox getHandBox() {return handBox;}
    public Hand getHand() {return hand;}

    // To flip cards
    public void flipOpen() {
        this.showHand = true;
        for (CardDisplay cD: cardList) {
            cD.flipOpen();;
        }
    }
//        for (int i = 0; i < getHandBox().getChildren().size(); i++) {
//            Node n = getHandBox().getChildren().get(i);
//            if (n instanceof CardDisplay) {
//
//            }
//
//        }
    public void flipClose() {
        this.showHand = false;
        for (CardDisplay cD: cardList) {
            cD.flipClose();
        }
    }

    public void addCard(Card card) {
        CardDisplay cD = new CardDisplay(this.channel, card, CARD_SIZEW, CARD_SIZEH);
        cardList.add(cD);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/CardDisplay.fxml"));
            loader.setControllerFactory(c -> cD);
            this.handBox.getChildren().add(loader.load());
        } catch (Exception e) {
            System.out.println("Hand failed to add card!");
            System.out.println("Check Name " + cD.getCard().getName() );
            System.out.println("Check Element " + cD.getCard().getElement().toString());
            System.out.println("Check Desc " + cD.getCard().getDesc());
            System.out.println("Check PATH " + cD.getCard().getImgPath());
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
    }

    public void onDrawEvent(DrawEvent event) {
        if (hand.getPlayer() == event.h) this.addCard(event.c);
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
