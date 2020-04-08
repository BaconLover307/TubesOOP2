package com.avatarduel.view;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import com.avatarduel.model.cards.Destroy;
import com.avatarduel.view.SkillDisplay;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;

public class DestroyDisplay extends SkillDisplay {

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

    public DestroyDisplay(Destroy C, Pane pane, double cardW, double cardH, double posX, double posY){
        
        super(C,pane,cardW,cardH,posX,posY);

        effSz = 0.03 * cardH;
        effX = 0.1 * cardW;
        effY = 0.21 * cardH;
        effect = new Text();
        effect.setText("DESTROY");
        effect.setFont(Font.font(java.awt.Font.SERIF, effSz));
        effect.setX(effX);
        effect.setY(effY);

        attrX = 0.08 * cardW;
        attrY = 0.95 * cardH;
        attrSz = 0.05 * cardH;
        attrW = 0.85 * cardW;
        atr = "POW/" + Integer.toString(C.getPowVal());
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