package com.avatarduel.view.cards;


import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.card.*;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.DisplayCardEvent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

import java.awt.event.MouseAdapter;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class CardDisplay implements Initializable, Publisher, Subscriber, Flippable {
    private static final Image CARD_BACK_IMAGE = new Image("com/avatarduel/asset/card-back.png");

    @FXML
    public Pane box;
    @FXML
    public Label card_name;
    public IntegerProperty name_font_size;
    public int name_size;
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

    @FXML
    public ImageView card_back;

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
        DoubleProperty scaleW = new SimpleDoubleProperty(cardWidth/400);
        DoubleProperty scaleH = new SimpleDoubleProperty(cardHeight/560);
        box.scaleXProperty().bind(scaleW);
        box.scaleYProperty().bind(scaleH);
        box.setPrefWidth(this.cardWidth * scaleW.doubleValue());
        box.setPrefHeight(this.cardHeight * scaleH.doubleValue());
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
                    "  POW/" + Integer.toString(((Character)this.card).getPowVal());
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
        this.box.getChildren().remove(this.box.getChildren().size()-1);

        box.setOnMouseMoved(e -> {if (this.show) doDisplayCard();});

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
        this.show = true;
        this.cardHeight = height;
        this.cardWidth = width;
    }

    public Pane getBox() {return this.box;}
    public Card getCard() {return this.card;}

    public void changeShowProperty() {this.show = !this.show;}

    public void doDisplayCard() {
        publish("DISPLAY_CARD", new DisplayCardEvent(this));
    }

    @Override
    public void flipOpen() {
        this.show = true;
        this.box.getChildren().remove(this.box.getChildren().size()-1);
    }

    @Override
    public void flipClose() {
        this.show = false;
        this.box.getChildren().add(new ImageView(CARD_BACK_IMAGE));
    }

    @Override
    public void publish(String topic, BaseEvent event) {this.channel.sendEvent(topic, event);}

    @Override
    public void onEvent(BaseEvent event) {

    }



}