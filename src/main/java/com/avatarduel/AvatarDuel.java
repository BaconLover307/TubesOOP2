package com.avatarduel;

import java.awt.Dimension;
import java.awt.Toolkit;

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


  @Override
  public void start(Stage stage) {
    window = stage;
    stage.setTitle("Avatar Duel - K3 G08");
    gameplay = new GameplayChannel();

    Button btnStart = new Button("START");
    Button btnSetting = new Button("SETTING");
    Button btnExit = new Button("EXIT");

    btnStart.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    btnStart.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        try {
          MainScreen(window);
        } catch (Exception err) {
          err.printStackTrace();
        }
      }
    });

    // TODO Delete after debugging
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

  class Settings {

    private int amount;

    void showSettings(int n) {
      Stage settings = new Stage();
      settings.initModality(Modality.APPLICATION_MODAL);
      settings.setTitle("Settings");

      amount = n;
      String txt = "Cards on Deck: ";
      Label cardLabel = new Label(txt + amount);
      Slider cardSlider = new Slider(40, 60, amount);
      cardSlider.setShowTickLabels(true);
      cardSlider.setShowTickMarks(true);
      cardSlider.setSnapToTicks(true);
      cardSlider.setMajorTickUnit(5);
      cardSlider.setBlockIncrement(1);
      cardSlider.setMaxWidth(250);
      cardSlider.valueProperty().addListener(
              (observable, oldValue, newValue) -> cardLabel.setText(txt + newValue.intValue())
      );

      Button btnSet = new Button("Set");
      Button btnCancel = new Button("Cancel");
      btnSet.setOnAction(e -> {
        amount = cardSlider.valueProperty().getValue().intValue();
        settings.close();
      });
      btnCancel.setOnAction(e -> settings.close());

      HBox confirm = new HBox(15);
      confirm.setAlignment(Pos.CENTER);
      confirm.getChildren().addAll(btnSet,btnCancel);
      VBox settingsBox = new VBox(10);
      settingsBox.setAlignment(Pos.CENTER);
      settingsBox.getChildren().addAll(cardLabel,cardSlider, confirm);
      Scene s = new Scene(settingsBox, 300, 150);
      settings.setScene(s);
      settings.showAndWait();
    }

    public int getCardAmount() {return amount;}
  }

  public void InitScreen(Stage stage) {

  }

  public void MainScreen(Stage stage) throws Exception{
    Pane pane = new Pane();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/avatarduel/fxml/MainPage.fxml"));
    loader.setControllerFactory(c -> new MainPageController(this.gameplay));
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