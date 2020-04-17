package com.avatarduel;
import com.avatarduel.model.cards.cardcollection.Deck;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;

import com.avatarduel.util.CSVReader;
import com.avatarduel.view.MainPageController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 

import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.view.Settings;
import com.avatarduel.view.InputPlayer;
import com.avatarduel.model.cards.card.Land;

public class AvatarDuel extends Application {
  private GameplayChannel gameplay;
  Stage window;
  Scene inGame, mainMenu;

  final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  final BackgroundSize backgroundSize = new BackgroundSize(screenSize.getWidth(), screenSize.getHeight(), false, false, false, false);
  final double cardDisW = (400*screenSize.getWidth()/1920);
  final double cardDisH = (560*screenSize.getHeight()/1080);
  final double cardDisPosX = (120*screenSize.getWidth()/1920);
  final double cardDisPosY = (260*screenSize.getHeight()/1080);
  final double cardSummonedW = (80*screenSize.getWidth()/1920);
  final double cardSummonedH = (112*screenSize.getHeight()/1080);
  private int cardAmount = 60;
  private String playerName1, playerName2;

  private static final String CHAR_CSV_FILE_PATH = "card/data/character.csv";
  private static final String AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";

  @Override
  public void start(Stage stage) {
    window = stage;
    stage.setTitle("Avatar Duel - K3 G08");
    gameplay = new GameplayChannel();


    Button btnStart = new Button("START");
    Button btnSetting = new Button("SETTINGS");
    Button btnExit = new Button("EXIT");

    btnStart.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    btnStart.setOnAction(e -> {
      try {
        InputPlayer inpP1 = new InputPlayer(1);
        inpP1.showInputPlayer();
        playerName1 = inpP1.getName();
        InputPlayer inpP2 = new InputPlayer(2);
        inpP2.showInputPlayer();
        playerName2 = inpP2.getName();
        if (playerName1.equals(playerName2)) {
          playerName2 = playerName2 + "*";
        }
        MainScreen(window);
      } catch (Exception err) {
        err.printStackTrace();
      }
    });

    btnSetting.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    btnSetting.setOnAction(e -> {
      Settings set = new Settings();
      set.showSettings(cardAmount);
      cardAmount = set.getCardAmount();
    });

    btnExit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    btnExit.setOnAction(e -> stage.close());

    TilePane mainmenu = new TilePane(Orientation.VERTICAL);
    mainmenu.setPadding(new Insets(screenSize.getHeight()/2 - 50, 10, 10,(screenSize.getWidth()/2)-105));
    mainmenu.setHgap(12.0);
    mainmenu.setVgap(10.0);
    mainmenu.getChildren().addAll(btnStart, btnSetting, btnExit);
    Image image = new Image("com/avatarduel/asset/main-menu.png");
    BackgroundImage backgroundImage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize);
    Background background = new Background(backgroundImage);
    mainmenu.setBackground(background);

    Scene s = new Scene(mainmenu);
    s.getStylesheets().add(getClass().getResource("/com/avatarduel/css/mainStyle.css").toExternalForm());
    window.setScene(s);
    window.setMaximized(true);
    window.show();
    /*
    try {
      this.loadCards();
      text.setText("Avatar Duel!");
    } catch (Exception e) {
      text.setText("Failed to load cards: " + e);
    } */
  }

  public void MainScreen(Stage stage) throws Exception{
    Pane pane = new Pane();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/avatarduel/fxml/MainPage.fxml"));
    loader.setControllerFactory(c -> new MainPageController(this.gameplay, cardAmount, playerName1, playerName2));
    Pane main = loader.load();
    pane.getChildren().add(main);
    Scene scene = new Scene(pane);
    Image image = new Image("com/avatarduel/asset/board.png");
    BackgroundImage backgroundImage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize);
    Background background = new Background(backgroundImage);
    pane.setBackground(background);

    stage.setScene(scene);
    stage.setMaximized(true);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}