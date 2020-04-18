package com.avatarduel.view;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.Phase;
import com.avatarduel.model.cards.card.*;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.DiscardEvent;
import com.avatarduel.model.gameplay.events.DrawEvent;
import com.avatarduel.model.gameplay.events.UseLandEvent;
import com.avatarduel.view.cards.CardDisplay;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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

public class HandDisplay implements BaseView, Flippable, Publisher, Subscriber,
        DrawEvent.DrawEventHandler,
//        DiscardEvent.DiscardEventHandler,
        UseLandEvent.UseLandEventHandler {

    private Hand hand;
    private ArrayList<CardDisplay> cardList;
    private GameplayChannel channel;
    private boolean showHand;
    private HBox handBox;

    public HandDisplay(GameplayChannel gameplayChannel, Hand hand) {
        this.showHand = false;
        this.channel = gameplayChannel;
        this.channel.addSubscriber("DRAW_EVENT", this);
        this.channel.addSubscriber("USE_LAND", this);
//        this.channel.addSubscriber("DISCARD", this);

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

    public void flipClose() {
        this.showHand = false;
        for (CardDisplay cD: cardList) {
            cD.flipClose();
        }
    }

    public void addCard(Card card) {
        CardDisplay cD = new CardDisplay(this.channel, card, CARD_SIZEW, CARD_SIZEH);
        cardList.add(cD);
        this.hand.addCard(card);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/CardDisplay.fxml"));
            loader.setControllerFactory(c -> cD);
            Pane toLoad = loader.load();
//            toLoad.setPrefWidth(CARD_SIZEW);
            toLoad.setOnMouseClicked(event -> {
                if (this.showHand && this.channel.phase == Phase.MAIN_PHASE) selectCard(cD);
            });
            this.handBox.getChildren().add(toLoad);
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

    public void removeCard(Card card) {
        int i = 0;
        for (CardDisplay cD: cardList) {
            if (cD.getCard().equals(card)) {
                cardList.remove(i);
                break;
            }
            i++;
        }
        if (i < this.handBox.getChildren().size()) this.handBox.getChildren().remove(i);
    }

    public void selectCard(CardDisplay cD) {
        if (cD.getCard() instanceof Land) {
            AlertChoice landChoice = new AlertChoice("Use Land", "Discard", ("Land Card " + cD.getCard().getName() + " selected."), "Land Card");
            String ret = landChoice.showAndReturn();
            if (ret.equals("Use Land")) {
                if (this.hand.isUsedLand()) {
                    AlertPlayer hasUsedLand = new AlertPlayer("You have used a Land card in this turn!", Alert.AlertType.WARNING, "Used Land!");
                    hasUsedLand.show();
                } else {
                    this.hand.doUseLand((Land) cD.getCard());
                }
            } else if (ret.equals("Discard")) {
                this.removeCard(cD.getCard());
                this.hand.removeCard(cD.getCard());
                this.publish("DISCARD", new DiscardEvent(cD.getCard(), this.getHand().getPlayer()));
            }
        } else if (cD.getCard() instanceof Character) {
            AlertChoice landChoice = new AlertChoice("Summon Character", "Discard", ("Character Card " + cD.getCard().getName() + " selected."), "Character Card");
            String ret = landChoice.showAndReturn();
            if (ret.equals("Summon Character")) {
                // TODO Publish Request Summon Char
//                if (this.hand.isUsedLand()) {
//                    AlertPlayer hasUsedLand = new AlertPlayer("You have used a Land card in this turn!", Alert.AlertType.WARNING, "Used Land!");
//                    hasUsedLand.show();
//                } else {
//                    this.hand.doUseLand((Land) cD.getCard());
//                }
                System.out.println("Nanti request ya..");
            } else if (ret.equals("Discard")) {
                this.removeCard(cD.getCard());
                this.hand.removeCard(cD.getCard());
                this.publish("DISCARD", new DiscardEvent(cD.getCard(), this.getHand().getPlayer()));
            }
        } else if (cD.getCard() instanceof Skill) {
            AlertChoice landChoice = new AlertChoice("Summon Skill", "Discard", ("Skill Card " + cD.getCard().getName() + " selected."), "Skill Card");
            String ret = landChoice.showAndReturn();
            if (ret.equals("Summon Skill")) {
                // TODO Publish Request Summon SKill
//                if (this.hand.isUsedLand()) {
//                AlertPlayer hasUsedLand = new AlertPlayer("You have used a Land card i?n this turn!", Alert.AlertType.WARNING, "Used Land!");
//                    hasUsedLand.show();
//                } else {
//                    this.hand.doUseLand((Land) cD.getCard());
//                }
                System.out.println("Nanti request ya..");
            } else if (ret.equals("Discard")) {
                this.removeCard(cD.getCard());
                this.hand.removeCard(cD.getCard());
                this.publish("DISCARD", new DiscardEvent(cD.getCard(), this.getHand().getPlayer()));
            }
        }
    }

    @Override
    public void onDrawEvent(DrawEvent e) {
        if (hand.getPlayer() == e.h) this.addCard(e.c);
    }

    @Override
    public void onUseLandEvent(UseLandEvent e) {
        if (hand.getPlayer() == e.owner) {
            this.removeCard(e.land);
        }
    }

//    @Override
//    public void onDiscard(DiscardEvent e) {
//        if (hand.getPlayer() == e.owner) {
//            this.removeCard(e.card);
//            this.hand.removeCard(e.card);
//        }
//    }

    @Override
    public void publish(String topic, BaseEvent event) {this.channel.sendEvent(topic, event);}

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof DrawEvent) {
            onDrawEvent((DrawEvent) event);
        } else if (event instanceof UseLandEvent) {
            onUseLandEvent((UseLandEvent) event);
//        } else if (event instanceof DiscardEvent) {
//            onDiscard((DiscardEvent) event);
        }
    }
}
