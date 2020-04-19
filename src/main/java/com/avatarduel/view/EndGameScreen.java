package com.avatarduel.view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.geometry.Orientation;
import java.awt.Toolkit;

import com.avatarduel.view.BaseView;

import javafx.scene.image.Image;
import javafx.geometry.Insets;
import java.awt.Dimension;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class EndGameScreen implements BaseView {

    private BackgroundSize backgroundSize = new BackgroundSize(SCREENW, SCREENH, false, false, false, false);

    public EndGameScreen(String winner) {

        TilePane endmenu = new TilePane(Orientation.VERTICAL);
        endmenu.setPadding(new Insets(SCREENH/2 - 50, 10, 10,(SCREENW/2)-105));
        Image image = new Image("com/avatarduel/asset/main-menu.png");
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize);
        Background background = new Background(backgroundImage);
        endmenu.setBackground(background);

        Text text = new Text();
        text.setText("CONGRATULATION! " + winner + " WIN THIS GAME!");
        text.setX(SCREENW/5-(50));
        text.setY(SCREENH/2);
        text.setStyle(
            "-fx-text-fill: #DFD7D1;" +
            "-fx-font-family: Herculanum;" +
            "-fx-font-size: 50;" 
        );

        endmenu.getChildren().add(text);

        Scene s = new Scene(endmenu);
        Stage stage = new Stage();
        stage.setTitle("Avatar Duel - K3 G08");
        stage.setScene(s);
        stage.setMaximized(true);
        stage.show();
    }

}