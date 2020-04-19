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


public class EndGameScreen implements BaseView {

    private BackgroundSize backgroundSize = new BackgroundSize(SCREENW, SCREENH, false, false, false, false);

    public EndGameScreen() {

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

        Scene s = new Scene(endmenu);
        Stage stage = new Stage();
        stage.setScene(s);
        stage.setMaximized(true);
        stage.show();
    }

}