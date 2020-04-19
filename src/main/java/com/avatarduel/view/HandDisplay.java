package com.avatarduel.view;

import com.avatarduel.model.Phase;
import com.avatarduel.model.cards.card.*;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;

import java.util.ArrayList;

import com.avatarduel.model.cards.cardcollection.Hand;

public class HandDisplay implements BaseView, Flippable, Publisher, Subscriber,
        DrawEvent.DrawEventHandler,
        SummonCharacterEvent.SummonCharacterEventHandler,
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
        this.channel.addSubscriber("SUMMON_CHARACTER", this);
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/CardDisplay.fxml"));
            loader.setControllerFactory(c -> cD);
            Pane toLoad = loader.load();
//            toLoad.setPrefWidth(CARD_SIZEW);
            toLoad.setOnMouseClicked(event -> {
                if (this.showHand && this.channel.phase == Phase.MAIN_PHASE && !this.channel.isSelecting) selectCard(cD);
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
                if (this.channel.activePlayer.getPowers().getPower(cD.getCard().getElement()).getSize() >= ((Character) cD.getCard()).getPowVal()) {
                    if (channel.activePlayer.getBoard().isCharSlotAvailable()) {
                        publish("REQUEST_SUMMON", new RequestSummonEvent(cD.getCard(), channel.activePlayer.getName()));
                        this.channel.isSelecting = true;
                    } else {
                        AlertPlayer noSlot = new AlertPlayer("Character slot unavailable!", Alert.AlertType.WARNING, "Slot Unavailable");
                        noSlot.show();
                    }
                } else {
                    AlertPlayer insufficientPower = new AlertPlayer("Your " + cD.getCard().getElement() + " power is not enough!" , Alert.AlertType.WARNING, "Insufficient Power");
                    insufficientPower.show();
                }
            } else if (ret.equals("Discard")) {
                this.removeCard(cD.getCard());
                this.hand.removeCard(cD.getCard());
                this.publish("DISCARD", new DiscardEvent(cD.getCard(), this.getHand().getPlayer()));
            }

        } else if (cD.getCard() instanceof Skill) {
            AlertChoice landChoice = new AlertChoice("Summon Skill", "Discard", ("Skill Card " + cD.getCard().getName() + " selected."), "Skill Card");
            String ret = landChoice.showAndReturn();
            if (ret.equals("Summon Skill")) {
                if (this.channel.activePlayer.getPowers().getPower(cD.getCard().getElement()).getSize() >= ((Skill) cD.getCard()).getPowVal()) {
                    if (channel.activePlayer.getBoard().isSkillSlotAvailable()) {
                        publish("REQUEST_SUMMON", new RequestSummonEvent(cD.getCard(), channel.activePlayer.getName()));
                    } else {
                        AlertPlayer noSlot = new AlertPlayer("Skill slot unavailable!", Alert.AlertType.WARNING, "Slot Unavailable");
                        noSlot.show();
                    }
                } else {
                        AlertPlayer insufficientPower = new AlertPlayer("Your " + cD.getCard().getElement() + " power is not enough!" , Alert.AlertType.WARNING, "Insufficient Power");
                        insufficientPower.show();
                }
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

    @Override
    public void onSummonCharacterEvent(SummonCharacterEvent e) {
        if (hand.getPlayer() == e.owner) {
            this.removeCard(e.C);
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
        } else if (event instanceof SummonCharacterEvent) {
            onSummonCharacterEvent((SummonCharacterEvent) event);
//        } else if (event instanceof DiscardEvent) {
//            onDiscard((DiscardEvent) event);
        }
    }
}
