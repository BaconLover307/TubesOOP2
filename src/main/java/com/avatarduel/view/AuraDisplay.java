package com.avatarduel.view;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import com.avatarduel.model.cards.card.Aura;
import com.avatarduel.view.CardDisplay;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;

public class AuraDisplay extends CardDisplay {

    private Text atribut;
    private double attrX;
    private double attrY;
    private double attrSz;
    private double attrW;
    private double effX;
    private double effY;
    private double effSz;
    private String atr;
    private Text effect;

    public AuraDisplay(Aura C, Pane pane, double cardW, double cardH, double posX, double posY){
        
        super(C,pane,cardW,cardH,posX,posY);

        Image img = new Image("com/avatarduel/asset/card-skill.png");
        BackgroundSize backgroundSize = new BackgroundSize(cardW, cardH, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(img,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize);
        Background background = new Background(backgroundImage);
        super.getBox().setBackground(background);

        effSz = 0.03 * cardH;
        effX = 0.1 * cardW;
        effY = 0.21 * cardH;
        effect = new Text();
        effect.setText("AURA");
        effect.setFont(Font.font(java.awt.Font.SERIF, effSz));
        effect.setX(effX);
        effect.setY(effY);

        attrX = 0.08 * cardW;
        attrY = 0.95 * cardH;
        attrSz = 0.035 * cardH;
        attrW = 0.85 * cardW;
        atr = "+" + Integer.toString(C.getAttVal()) + " ATK  " + "+" + Integer.toString(C.getDefVal()) + " DEF" + "  POW/" + Integer.toString(C.getPowVal());
        atribut = new Text();
        atribut.setText(atr);
        atribut.setFont(Font.font(java.awt.Font.SERIF, attrSz));
        atribut.setTextAlignment(TextAlignment.RIGHT);
        atribut.wrappingWidthProperty().set(attrW);
        atribut.setX(attrX);
        atribut.setY(attrY);

        super.getBox().getChildren().addAll(atribut,effect);
    }

  
    
}