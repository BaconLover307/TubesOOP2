package com.avatarduel.view.cards;


import com.avatarduel.AvatarDuel;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.DisplayCardEvent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
//import java.awt.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;

import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Land;
import com.avatarduel.model.cards.card.Aura;
import com.avatarduel.model.cards.card.Destroy;
import com.avatarduel.model.cards.card.PowerUp;

import java.awt.event.MouseAdapter;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class CardDisplay implements Initializable, Publisher, Subscriber {
    @FXML
    public Pane box;
    @FXML
    public Label card_name;
    @FXML
    public ImageView card_bg;
    @FXML
    public ImageView card_element;
    @FXML
    public ImageView card_image;
    @FXML
    public Label card_desc;
    @FXML
    public Label card_attribute;
    @FXML
    public Label card_skillType;
    @FXML
    public String name;

    private Card card;
    private Image bg;
    private double cardHeight;
    private double cardWidth;
    private DoubleProperty cardW;
    private DoubleProperty cardH;

    private boolean show;
    private GameplayChannel channel;
    private int n;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.card_name.setText(card.getName());
        this.card_image.setImage(new Image(card.getImgPath()));
        String elpath;
        switch (card.getElement()) {
            case AIR: elpath = "com/avatarduel/asset/elm-air.png"; break;
            case WATER: elpath = "com/avatarduel/asset/elm-water.png"; break;
            case FIRE: elpath = "com/avatarduel/asset/elm-fire.png"; break;
            case EARTH: elpath = "com/avatarduel/asset/elm-earth.png"; break;
            default: elpath = "com/avatarduel/asset/elm-energy.png"; break;
        }
        Image img_el = new Image(elpath);
        this.card_element.setImage(img_el);
        this.card_desc.setText(card.getDesc());

        if (card instanceof Character) {
            this.bg = new Image("com/avatarduel/asset/card-character.png");
            String atr = "ATK/" + Integer.toString(((Character)this.card).getAttack()) +
                    "  DEF/" +Integer.toString(((Character)this.card).getDefense()) +
                    "  POW/" + Integer.toString(((Character)this.card).getPower());
            this.card_attribute.setText(atr);
        }
        else if (card instanceof Land) {
            this.bg = new Image("com/avatarduel/asset/card-land.png");
        }
        else {
            this.bg = new Image("com/avatarduel/asset/card-skill.png");
            String atr;
            if (card instanceof Aura) {
                this.card_skillType.setText("[Aura]");
                atr = "+" + Integer.toString(((Aura)this.card).getAttVal()) + " ATK  " +
                        "+" + Integer.toString(((Aura)this.card).getDefVal()) + " DEF" +
                        "  POW/" + Integer.toString(((Aura)this.card).getPowVal());
            }
            else if (card instanceof PowerUp) {
                this.card_skillType.setText("[PowerUp]");
                atr = "POW/" + Integer.toString(((PowerUp)this.card).getPowVal());
            }
            else {
                this.card_skillType.setText("[Destroy]");
                atr = "POW/" + Integer.toString(((Destroy)this.card).getPowVal());
            }
            this.card_attribute.setText(atr);
        }

        this.card_bg.setImage(this.bg);



        this.card_bg.fitWidthProperty().bind(box.widthProperty());
        this.card_bg.fitHeightProperty().bind(box.heightProperty());
//        this.card_image.layoutXProperty().bind(box.widthProperty().multiply((double)50/400));
//        this.card_image.layoutYProperty().bind(box.heightProperty().multiply((double)122/560));
//
        this.card_image.fitWidthProperty().bind(box.widthProperty().multiply((double) 300/400));
        this.card_image.fitHeightProperty().bind(box.heightProperty().multiply((double) 240/560));
        this.card_image.xProperty().bind(box.widthProperty().multiply((double) 50/400));
        this.card_image.yProperty().bind(box.heightProperty().multiply((double) 122/560));
//
        this.card_element.fitWidthProperty().bind(box.widthProperty().multiply((double) 46/400));
        this.card_element.fitHeightProperty().bind(box.heightProperty().multiply((double) 46/400));
        this.card_element.xProperty().bind(box.widthProperty().multiply((double)329/400));
        this.card_element.yProperty().bind(box.heightProperty().multiply((double)25/560));
//
        this.card_name.prefWidthProperty().bind(box.widthProperty().multiply((double)280/400));
        this.card_name.prefHeightProperty().bind(box.heightProperty().multiply((double)48/560));
//        this.card_name.relocate(box.getWidth() * ((double)329/400), box.getHeight() * ((double)25/560));
//        this.card_name.layoutXProperty().bind(box.widthProperty().multiply((double)30/400));
//        this.card_name.layoutYProperty().bind(box.heightProperty().multiply((double)23/560));
//
        this.card_desc.prefWidthProperty().bind(box.widthProperty().multiply((double)340/400));
        this.card_desc.prefHeightProperty().bind(box.heightProperty().multiply((double)110/560));
////        this.card_desc.layoutXProperty().bind(box.widthProperty().multiply((double)30/400));
////        this.card_desc.layoutYProperty().bind(box.heightProperty().multiply((double)394/560));
//
        this.card_attribute.prefWidthProperty().bind(box.widthProperty().multiply((double)350/400));
        this.card_attribute.prefHeightProperty().bind(box.heightProperty().multiply((double)34/560));
//        this.card_attribute.layoutXProperty().bind(box.widthProperty().multiply((double)25/400));
//        this.card_attribute.layoutYProperty().bind(box.heightProperty().multiply((double)505/560));
//
        this.card_skillType.prefWidthProperty().bind(box.widthProperty().multiply((double)82/400));
        this.card_skillType.prefHeightProperty().bind(box.heightProperty().multiply((double)48/560));
//        this.card_skillType.layoutXProperty().bind(box.widthProperty().multiply((double)50/400));
//        this.card_skillType.layoutYProperty().bind(box.heightProperty().multiply((double)82/560));

//        this.cardW = new SimpleDoubleProperty(box.widthProperty().doubleValue());
//        this.cardH = new SimpleDoubleProperty(box.heightProperty().doubleValue());
//        this.cardW.bind(box.widthProperty());
//        this.cardH.bind(box.heightProperty());
//
//        BackgroundSize backgroundSize = new BackgroundSize(cardW.doubleValue(), cardH.doubleValue(), false, false, false, false);
//        BackgroundImage backgroundImage = new BackgroundImage(this.bg,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.DEFAULT,
//                backgroundSize);
//        Background background = new Background(backgroundImage);
//        box.setBackground(background);
        box.setOnMouseMoved(e -> {if (this.show) doDisplayCard();});
        box.setPrefWidth(this.cardWidth);
        box.setPrefHeight(this.cardHeight);
//        resizeCard();
    }

    public CardDisplay(GameplayChannel gameplayChannel, Card C) {
        this.channel = gameplayChannel;
        this.card = C;
        this.show = false;
        this.cardHeight = 560;
        this.cardWidth = 400;
    }

    public CardDisplay(GameplayChannel gameplayChannel, Card C, double width, double height) {
        this.channel = gameplayChannel;
        this.card = C;
        this.show = false;
        this.cardHeight = height;
        this.cardWidth = width;
    }

    public Card getCard() {return this.card;}
    public void showCard() {this.show = true;}
    public void hideCard() {this.show = false;}
    public void changeShowProperty() {this.show = !this.show;}

    public void doDisplayCard() {
        publish("DISPLAY_CARD", new DisplayCardEvent(this));
    }

    @Override
    public void publish(String topic, BaseEvent event) {this.channel.sendEvent(topic, event);}

    @Override
    public void onEvent(BaseEvent event) {

    }

    public void resizeCard(int width, int height) {

//        this.card_image.fitWidthProperty().bind(box.widthProperty().multiply((double)300/400));
//        this.card_image.fitHeightProperty().bind(card_image.fitWidthProperty());
//        this.card_image.relocate(box.getWidth() * ((double)50/400), box.getHeight() * ((double)122/560));
//
//        this.card_element.fitWidthProperty().bind(box.widthProperty().multiply((double)46/400));
//        this.card_element.fitHeightProperty().bind(card_element.fitWidthProperty());
//        this.card_element.relocate(box.getWidth() * ((double)329/400), box.getHeight() * ((double)25/560));
//
//        this.card_name.prefWidthProperty().bind(box.widthProperty().multiply((double)280/400));
//        this.card_name.prefHeightProperty().bind(box.heightProperty().multiply((double)48/560));
//        this.card_name.relocate(box.getWidth() * ((double)329/400), box.getHeight() * ((double)25/560));
//
//        this.card_desc.prefWidthProperty().bind(box.widthProperty().multiply((double)340/400));
//        this.card_desc.prefHeightProperty().bind(box.heightProperty().multiply((double)110/560));
//        this.card_desc.relocate(box.getWidth() * ((double)30/400), box.getHeight() * ((double)394/560));
//
//        this.card_attribute.prefWidthProperty().bind(box.widthProperty().multiply((double)350/400));
//        this.card_attribute.prefHeightProperty().bind(box.heightProperty().multiply((double)34/560));
//        this.card_attribute.relocate(box.getWidth() * ((double)25/400), box.getHeight() * ((double)505/560));
//
//        this.card_skillType.prefWidthProperty().bind(box.widthProperty().multiply((double)82/400));
//        this.card_skillType.prefHeightProperty().bind(box.heightProperty().multiply((double)48/560));
//        this.card_skillType.relocate(box.getWidth() * ((double)50/400), box.getHeight() * ((double)82/560));

//        box.setVisible(true);
//        pane.getChildren().add(box);
//        box.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
//            n++;
////            name.setText("MOVED " + Integer.toString(n));
//            box.setVisible(true);
//
//        });
//        box.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
//            box.setVisible(false);
//        });

    }

    public Pane getBox() {return this.box;}



}