package com.avatarduel.view;


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

import com.avatarduel.model.cards.Card;
import javafx.scene.text.TextAlignment;


public class CardDisplay {
    
    private Text name;
    private ImageView element;
    private ImageView image;
    private Text desc;
    private Text attr;
    private double cardH;
    private double cardW;
    private double nameSz;
    private double nameX;
    private double nameY;
    private double elmSz;
    private double elmX;
    private double elmY;
    private double imgW;
    private double imgH;
    private double imgX;
    private double imgY;
    private double descW;
    private double descSz;
    private double descX;
    private double descY;

    public CardDisplay(Card C, Pane hbox, double cardW, double cardH, int posX, int posY) {
        this.cardW = cardW;
        this.cardH = cardH;
        Pane box = new Pane();
        box.setPrefSize(cardW,cardH);
        box.relocate(posX, posY);
        Image img = new Image("com/avatarduel/asset/card-character.png");
        BackgroundSize backgroundSize = new BackgroundSize(cardW, cardH, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(img,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize);
        Background background = new Background(backgroundImage);
        box.setBackground(background);

        nameSz = 0.075 * cardH;
        nameX = 0.0875 * cardW;
        nameY = 0.11 * cardH;
        name = new Text();
        name.setText(C.getName());
        name.setFont(Font.font(java.awt.Font.SERIF, nameSz));
        name.setX(nameX);
        name.setY(nameY);

        String elpath;
        switch (C.getElement()) {
            case AIR: elpath = "com/avatarduel/asset/elm-air.png"; break;
            case WATER: elpath = "com/avatarduel/asset/elm-water.png"; break;
            case FIRE: elpath = "com/avatarduel/asset/elm-fire.png"; break;
            default: elpath = "com/avatarduel/asset/elm-earth.png"; break;
        }
        elmX = 0.8225 * cardW;
        elmY = 0.0447 * cardH;
        elmSz = 0.115 * cardW;
        Image img_elm = new Image(elpath);
        element = new ImageView(img_elm);
        element.setFitWidth(elmSz);
        element.setFitHeight(elmSz);
        element.setX(elmX);
        element.setY(elmY);

        imgW = 0.75 * cardW;
        imgH = 0.4286 * cardH;
        imgX = 0.125 * cardW;
        imgY = 0.2178 * cardH;
        image = new ImageView(C.getImgPath());
        image.setFitWidth(imgW);
        image.setFitHeight(imgH);
        image.setX(imgX);
        image.setY(imgY);

        descX = 0.08 * cardW;
        descY = 0.735 * cardH;
        descW = 0.85 * cardW;
        descSz = 0.034 * cardH;
        desc = new Text();
        desc.setText(C.getDesc());
        desc.setFont(Font.font(java.awt.Font.SERIF, descSz));
        desc.setTextAlignment(TextAlignment.JUSTIFY);
        desc.wrappingWidthProperty().set(descW);
        desc.setX(descX);
        desc.setY(descY);

        box.getChildren().addAll(name,element,image,desc);
        hbox.getChildren().add(box);
        
    }
    


}