package com.avatarduel.view;

import javafx.scene.control.Label;
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

        BorderPane endmenu = new BorderPane();
        Image image = new Image("com/avatarduel/asset/main-menu.png");
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize);
        Background background = new Background(backgroundImage);
        endmenu.setBackground(background);


        Label text = new Label("CONGRATULATION! \n" + winner + " wins!");
        text.setStyle(
            "-fx-text-fill: #302C28;" +
            "-fx-font-family: 'Times New Roman';" +
            "-fx-font-size: 70;" +
            "-fx-text-alignment: center;"
        );

        endmenu.setCenter(text);

        Scene s = new Scene(endmenu);
        Stage stage = new Stage();
        stage.setTitle("Avatar Duel - K3 G08");
        stage.setScene(s);
        stage.setMaximized(true);
        stage.show();
    }

}