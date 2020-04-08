package com.avatarduel.view;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Map;
import com.avatarduel.model.cards.cardcollection.Hand;

public class HandDisplay {
    private Hand hand;
    private boolean showHand;
    private double handW,handH,handX,handY;

    public HandDisplay(Hand hand, double handW, double handH, double handX, double handY) {
        this.handW = handW; this.handH = handH; this.handX = handX; this.handY = handY;

    }
}
