package com.avatarduel.view.cards;


import com.avatarduel.model.gameplay.GameplayChannel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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


public class CardDisplay {
    private Card card;
    private Pane box;

    public Image card_bg;
    public Label card_name;
    public ImageView card_element;
    public ImageView card_image;
    public Label card_desc;
    public Label card_attribute;
    public Label card_skillType;
    public String name;

    private boolean show;
    private GameplayChannel channel;
    private int n;

    public CardDisplay(GameplayChannel gameplayChannel) {
        this.channel = gameplayChannel;
    }

    public void initCard(Card C, boolean show) {

        this.card = C;
        this.show = show;
        this.card_name = new Label();
        this.card_name.setText(this.name);
        this.card_image.setImage(new Image(C.getImgPath()));
        String elpath;
        switch (card.getElement()) {
            case AIR: elpath = "com/avatarduel/asset/elm-air.png"; break;
            case WATER: elpath = "com/avatarduel/asset/elm-water.png"; break;
            case FIRE: elpath = "com/avatarduel/asset/elm-fire.png"; break;
            case EARTH: elpath = "com/avatarduel/asset/elm-earth.png"; break;
            default: elpath = "com/avatarduel/asset/elm-energy.png"; break;
        }
        this.card_element.setImage(new Image(elpath));
        this.card_desc.setText(card.getDesc());
//        FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("CardDisplay.fxml"));
        if (card instanceof Character) {
            this.card_bg = new Image("com/avatarduel/asset/card_character.png");
            String atr = "ATK/" + Integer.toString(((Character)this.card).getAttack()) +
                    "  DEF/" +Integer.toString(((Character)this.card).getDefense()) +
                    "  POW/" + Integer.toString(((Character)this.card).getPower());
            this.card_attribute.setText(atr);
        }
        else if (card instanceof Land) {
            this.card_bg = new Image("com/avatarduel/asset/card_land.png");
        }
        else {
            this.card_bg = new Image("com/avatarduel/asset/card_skill.png");
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
        this.box.widthProperty().addListener(e -> {
            card_name.setFont(new Font(java.awt.Font.SERIF, (double)36/500 * this.box.getWidth()));
            card_desc.setFont(new Font(java.awt.Font.SERIF, (double)19/500 * this.box.getWidth()));
            card_attribute.setFont(new Font(java.awt.Font.SERIF, (double)24/500 * this.box.getWidth()));
            card_skillType.setFont(new Font(java.awt.Font.SERIF, (double)18/500 * this.box.getWidth()));
        });

        //this.card_bg.fitWidthProperty().bind(box.widthProperty());
        //this.card_bg.fitHeightProperty().bind(box.heightProperty());
        
        BackgroundSize backgroundSize = new BackgroundSize(box.getWidth(), box.getHeight(), false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(this.card_bg,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize);
        Background background = new Background(backgroundImage);
        box.setBackground(background);

        this.card_image.fitWidthProperty().bind(box.widthProperty().multiply((double)300/400));
        this.card_image.fitHeightProperty().bind(card_image.fitWidthProperty());
        this.card_image.relocate(box.getWidth() * ((double)50/400), box.getHeight() * ((double)122/560));

        this.card_element.fitWidthProperty().bind(box.widthProperty().multiply((double)46/400));
        this.card_element.fitHeightProperty().bind(card_element.fitWidthProperty());
        this.card_element.relocate(box.getWidth() * ((double)329/400), box.getHeight() * ((double)25/560));

        this.card_name.prefWidthProperty().bind(box.widthProperty().multiply((double)280/400));
        this.card_name.prefHeightProperty().bind(box.heightProperty().multiply((double)48/560));
        this.card_name.relocate(box.getWidth() * ((double)329/400), box.getHeight() * ((double)25/560));

        this.card_desc.prefWidthProperty().bind(box.widthProperty().multiply((double)340/400));
        this.card_desc.prefHeightProperty().bind(box.heightProperty().multiply((double)110/560));
        this.card_desc.relocate(box.getWidth() * ((double)30/400), box.getHeight() * ((double)394/560));

        this.card_attribute.prefWidthProperty().bind(box.widthProperty().multiply((double)350/400));
        this.card_attribute.prefHeightProperty().bind(box.heightProperty().multiply((double)34/560));
        this.card_attribute.relocate(box.getWidth() * ((double)25/400), box.getHeight() * ((double)505/560));

        this.card_skillType.prefWidthProperty().bind(box.widthProperty().multiply((double)82/400));
        this.card_skillType.prefHeightProperty().bind(box.heightProperty().multiply((double)48/560));
        this.card_skillType.relocate(box.getWidth() * ((double)50/400), box.getHeight() * ((double)82/560));

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