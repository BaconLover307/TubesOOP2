package com.avatarduel.view;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import com.avatarduel.model.cards.Skill;
import com.avatarduel.view.CardDisplay;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;

public class SkillDisplay extends CardDisplay {

    public SkillDisplay(Skill C, Pane pane, double cardW, double cardH, double posX, double posY){
        
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
    }

  
    
}