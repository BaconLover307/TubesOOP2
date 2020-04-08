package com.avatarduel.view;


import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import com.avatarduel.model.cards.Character;
import com.avatarduel.view.CardDisplay;
import javafx.scene.text.TextAlignment;

public class CharDisplay extends CardDisplay {

    private Text atribut;
    private double attrX;
    private double attrY;
    private double attrSz;
    private double attrW;
    private String atr;

    public CharDisplay(Character C, Pane pane, double cardW, double cardH, double posX, double posY){
        

        super(C,pane,cardW,cardH,posX,posY);
        attrX = 0.08 * cardW;
        attrY = 0.95 * cardH;
        attrSz = 0.05 * cardH;
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