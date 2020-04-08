package com.avatarduel.view.cards;


import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import com.avatarduel.model.cards.card.Land;
import com.avatarduel.view.cards.CardDisplay;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;

public class LandDisplay extends CardDisplay {

    public LandDisplay(Land C, Pane pane, double cardW, double cardH, double posX, double posY){

        super(C,pane,cardW,cardH,posX,posY);

        Image img = new Image("com/avatarduel/asset/card-land.png");
        BackgroundSize backgroundSize = new BackgroundSize(cardW, cardH, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(img,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize);
        Background background = new Background(backgroundImage);
        super.getBox().setBackground(background);
    }

  
    
}