package com.avatarduel.view.cards;


import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.view.cards.CardDisplay;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;

public class CharDisplay extends CardDisplay {

    private Text atribut;
    private double attrX, attrY, attrSz, attrW;
    private String atr;

    public CharDisplay(Character C, Pane pane, double cardW, double cardH, double posX, double posY){

        super(C,pane,cardW,cardH,posX,posY);

        Image img = new Image("com/avatarduel/asset/card-character.png");
        BackgroundSize backgroundSize = new BackgroundSize(cardW, cardH, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(img,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize);
        Background background = new Background(backgroundImage);
        super.getBox().setBackground(background);

        attrX = 0.08 * cardW;
        attrY = 0.95 * cardH;
        attrSz = 0.035 * cardH;
        attrW = 0.85 * cardW;
        atr = "ATK/" + Integer.toString(C.getAttack()) + "  DEF/" + Integer.toString(C.getDefense()) + "  POW/" + Integer.toString(C.getPower());
        atribut = new Text();
        atribut.setText(atr);
        atribut.setFont(Font.font(java.awt.Font.SERIF, attrSz));
        atribut.setTextAlignment(TextAlignment.RIGHT);
        atribut.wrappingWidthProperty().set(attrW);
        atribut.setX(attrX);
        atribut.setY(attrY);

        super.getBox().getChildren().add(atribut);
    }

  
    
}