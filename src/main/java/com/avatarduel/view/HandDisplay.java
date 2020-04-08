package com.avatarduel.view;

import com.avatarduel.model.cards.card.Flippable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Map;
import com.avatarduel.model.cards.cardcollection.Hand;

public class HandDisplay implements Flippable {
    private Hand hand;
    private boolean showHand;
    private double handW,handH,handX,handY;

    public HandDisplay(Hand hand, double handW, double handH, double handX, double handY) {
        this.handW = handW; this.handH = handH; this.handX = handX; this.handY = handY;
        this.showHand = false;
    }

    // Untuk menutup kartu
    public void flipOpen() {
        this.showHand = true;
    }
    public void flipClose() {
        this.showHand = false;
    }

}
