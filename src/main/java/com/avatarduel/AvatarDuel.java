package com.avatarduel;

import java.awt.Dimension;
import java.awt.Toolkit;
//import javafx.application.Application;
//import javafx.scene.Scene;
import com.avatarduel.view.CardDisplay;
import javafx.scene.image.Image;
//import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.TilePane;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.scene.layout.*;
import javafx.scene.text.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.avatarduel.model.Element;
//import com.avatarduel.model.Land;

import com.avatarduel.model.cards.Card;

public class AvatarDuel extends Application {

  final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  final BackgroundSize backgroundSize = new BackgroundSize(screenSize.getWidth(), screenSize.getHeight(), false, false, false, false);
  final int cardDisW = 400;
  final int cardDisH = 560;
  final int cardDisPosX = 120;
  final int cardDisPosY = 260;
  final int cardSummonedW = 80;
  final int cardSummonedH = 112;

  @Override
  public void start(Stage stage) {
    stage.setTitle("Avatar Duel - K3 G08");
//    InitScreen(stage);
    MainScreen(stage);
   
    /*
    Scene scene = new Scene(root, 1280, 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();

    try {
      this.loadCards();
      text.setText("Avatar Duel!");
    } catch (Exception e) {
      text.setText("Failed to load cards: " + e);
    } */
  }
  public void InitScreen(Stage stage) {
    Button startButton = new Button("START");
    Button settingButton = new Button("SETTING");
    Button exitButton = new Button("EXIT");

    startButton.setStyle("-fx-font-size: 15pt;");
    startButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    settingButton.setStyle("-fx-font-size: 15pt;");
    settingButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    exitButton.setStyle("-fx-font-size: 15pt;");
    exitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e)
      {   stage.close();
        MainScreen(stage);
      }
    };
    EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e)
      {System.out.println("setting"); }
    };
    EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e)
      {stage.close();}
    };
    startButton.setOnAction(event1);
    settingButton.setOnAction(event2);
    exitButton.setOnAction(event3);

    TilePane mainmenu = new TilePane(Orientation.VERTICAL);
    mainmenu.setPadding(new Insets(screenSize.getHeight()/2 - 50, 10, 10,screenSize.getWidth()/2 - 75));
    mainmenu.setHgap(12.0);
    mainmenu.setVgap(10.0);
    mainmenu.getChildren().addAll(startButton, settingButton, exitButton);
    Image image = new Image("com/avatarduel/asset/main-menu.png");
    BackgroundImage backgroundImage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize);
    Background background = new Background(backgroundImage);
    mainmenu.setBackground(background);

    Scene s = new Scene(mainmenu);
    s.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
    stage.setScene(s);
    stage.setMaximized(true);
    stage.show();
  }

  public void MainScreen(Stage stage) {
    Pane hbox = new Pane();
    Scene scene = new Scene(hbox);
    Image image = new Image("com/avatarduel/asset/board.png");
    BackgroundImage backgroundImage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize);
    Background background = new Background(backgroundImage);
    hbox.setBackground(background);
    Card card = new Card("Aang",Element.AIR,"lulu","com/avatarduel/card/image/character/Aang.png");
    CardDisplay DCard = new CardDisplay(card,hbox, cardDisW, cardDisH, cardDisPosX, cardDisPosY);
//    DCard.setX(100);
//    DCard.setY(100);
    stage.setTitle("Avatar Duel");
    stage.setScene(scene); 
    //stage.setFullScreen(true);
    stage.setMaximized(true);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}