package com.avatarduel.view;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import com.avatarduel.model.Phase;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Land;
import com.avatarduel.model.cards.card.Aura;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.ChangePhaseEvent;
import com.avatarduel.model.gameplay.events.ChangePlayerEvent;
import com.avatarduel.model.gameplay.events.DisplayCardEvent;
import com.avatarduel.model.gameplay.events.DrawEvent;
import com.avatarduel.model.gameplay.events.ResetPowerEvent;
import com.avatarduel.model.gameplay.events.SpendPowerEvent;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.player.Power;
import com.avatarduel.view.cards.CardDisplay;
//import com.avatarduel.view.cards.CharDisplay;
import com.avatarduel.util.CSVReader;
import com.sun.javafx.css.Stylesheet;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

import javax.naming.Name;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MainPageController implements Initializable, Publisher, Subscriber,
        ChangePhaseEvent.ChangePhaseEventHandler,
        DisplayCardEvent.DisplayCardEventHandler,
        DrawEvent.DrawEventHandler, ChangePlayerEvent.ChangePlayerEventHandler {
    private static final String CHAR_CSV_FILE_PATH = "../card/data/character.csv";
    private static final String LAND_CSV_FILE_PATH = "../card/data/land.csv";
    private static final String AURA_CSV_FILE_PATH = "../card/data/skill_aura.csv";
    private static final String CARD_FXML_PATH = "../fxml/CardDisplay.fxml";
    private static final String CUR_PHASE_STYLE_PATH = "com/avatarduel/css/curPhaseStyle.css";
    private static final String PHASE_STYLE_PATH = "com/avatarduel/css/phaseStyle.css";
    private static final double SCREENW = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double SCREENH = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double CARD_DISPLAY_SIZEW = SCREENW * 400 / 1920;
    private static final double CARD_DISPLAY_SIZEH = SCREENH * 560 / 1080;
    private static final double CARD_SIZEW = SCREENW * 80 / 1920;
    private static final double CARD_SIZEH = SCREENH * 112 / 1080;

    @FXML
    public AnchorPane root;
    @FXML
    private Pane cardPane;
    @FXML
    public Label infoLabel;

    @FXML
    public Label name1;
    @FXML
    public Label health1;
    private StringProperty deck1Count;
    @FXML
    public Label deck1;

    @FXML
    public Label name2;
    @FXML
    public Label health2;
    private StringProperty deck2Count;
    @FXML
    public Label deck2;

    private HandDisplay hand1Dis;
    @FXML
    public ScrollPane hand1Pane;
    @FXML
    public HBox hand1HBox;

    private HandDisplay hand2Dis;
    @FXML
    public ScrollPane hand2Pane;
    @FXML
    public HBox hand2HBox;
    @FXML
    public AnchorPane board1Pane;
    @FXML
    public AnchorPane board2Pane;

    private PowerDisplay power1Dis;
    @FXML
    public Pane power1Pane;

    private PowerDisplay power2Dis;
    @FXML
    public Pane power2Pane;

    @FXML
    public Label drawPhaseBox;
    @FXML
    public Label mainPhaseBox;
    @FXML
    public Label battlePhaseBox;
    @FXML
    public Label endPhaseBox;
    @FXML
    public Button btnNext;

    private GameplayChannel channel;
    private int cardAmount;
    private List<String[]> charCardList;
    private List<String[]> landCardList;
    private List<String[]> auraCardList;

    private Player player1;
    private Player player2;
    private Card display;
    private Card display2;
    private int turn;
    private Phase phase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // * Pane Setup
        DoubleProperty scaleW = new SimpleDoubleProperty(SCREENW/1920);
        DoubleProperty scaleH = new SimpleDoubleProperty(SCREENH/1080);
        root.scaleXProperty().bind(scaleW);
        root.scaleYProperty().bind(scaleH);
        root.setPrefWidth(SCREENW * scaleW.doubleValue());
        root.setPrefHeight(SCREENH * scaleH.doubleValue());

        // * Loads the CSV
        try {
            File charCSVFile = new File(getClass().getResource(CHAR_CSV_FILE_PATH).toURI());
            File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
            File auraCSVFile = new File(getClass().getResource(AURA_CSV_FILE_PATH).toURI());
            this.player1.getDeck().loadDeck(charCSVFile, auraCSVFile, landCSVFile, cardAmount);
            this.player2.getDeck().loadDeck(charCSVFile, auraCSVFile, landCSVFile, cardAmount);
        } catch (Exception e) {
            System.out.println("Failed to load CSV Files!");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }

        try {
        /*  // TESTING ALL POWER EVENT  
            Land l = new Land("as", Element.AIR, "LALA", "LALA");
            player1.getHand().doSelectLand(l);
            System.out.println(player1.getPower().getPower(Element.AIR).getSize());
            this.publish("SPEND_POWER_EVENT", new SpendPowerEvent(player1.getName(), Element.AIR, 1));
            this.publish("RESET_POWER_EVENT", new ResetPowerEvent(player1.getName()));
            Land l2 = new Land("as", Element.AIR, "LALA", "LALA");
            player2.getHand().doSelectLand(l2);
            System.out.println(player2.getPower().getPower(Element.AIR).getSize()); */
        } catch (Exception e) {e.printStackTrace();}

        // Power Display Setup
        power1Dis = new PowerDisplay(this.channel, player1.getPower());
        this.power1Pane.getChildren().add(power1Dis.getPane());
        power2Dis = new PowerDisplay(this.channel, player2.getPower());
        this.power2Pane.getChildren().add(power2Dis.getPane());

        this.hand1Dis = new HandDisplay(this.channel, player1.getHand());
        this.hand1HBox.getChildren().addAll(this.hand1Dis.getHandBox());
        this.hand2Dis = new HandDisplay(this.channel, player2.getHand());
        this.hand2HBox.getChildren().addAll(this.hand2Dis.getHandBox());
//        this.hand1HBox = this.hand1Dis.getHandBox();
//        this.hand2Dis = new HandDisplay(this.channel, player2.getHand());
////        this.hand2HBox = this.hand2Dis.getHandBox();
        for (int i = 0; i<16; i++) {
            this.player1.getDeck().doDraw();
            this.player2.getDeck().doDraw();
        }
//        this.hand1HBox = this.hand1Dis.getHandBox();
////        this.hand2HBox = this.hand2Dis.getHandBox();
//        System.out.println("SIZE HAND P1 = " + this.player1.getHand().getSize());
//        System.out.println("SIZE HAND P1 DI HANDDISPLAY = " + this.hand1Dis.getHand().getSize());
//        System.out.println("JUMLAH CHILD HAND P1 DI HANDDISPLAY = " + this.hand1Dis.getHandBox().getChildren().size());
//        System.out.println("JUMLAH CHILD HAND P1 DI hand1Hbox= " + this.hand1HBox.getChildren().size());


        // ! TESTING PURPOSES
//        FXMLLoader cardLoader = new FXMLLoader(getClass().getResource(CARD_FXML_PATH));
//        cardLoader.setControllerFactory(c -> new CardDisplay(this.channel, display));
//        try {
//            this.cardPane.getChildren().add(cardLoader.load());
//        } catch (Exception e) {
//            System.out.println("Error = " + e);
//        }
//        FXMLLoader cardLoader1 = new FXMLLoader(getClass().getResource(CARD_FXML_PATH));
//        CardDisplay coba1 = new CardDisplay(this.channel, display, 80, 112);
//        FXMLLoader cardLoader2 = new FXMLLoader(getClass().getResource(CARD_FXML_PATH));
//        CardDisplay coba2 = new CardDisplay(this.channel, display2, 80, 112);
//        coba1.showCard();
//        coba2.showCard();
//        cardLoader1.setControllerFactory(c -> coba1);
//        cardLoader2.setControllerFactory(c -> coba2);
//        try {
//            this.hand1HBox.getChildren().add(cardLoader1.load());
//            this.hand1HBox.getChildren().add(cardLoader2.load());
//        } catch (Exception e) {
//            System.out.println("Error = " + e);
//        }
//        this.name1.setText("abcdefghijkl");
        this.deck1Count = new SimpleStringProperty(Integer.toString(player1.getDeck().getSize()));
        deck1.textProperty().bind(deck1Count);
        this.deck2Count = new SimpleStringProperty(Integer.toString(player2.getDeck().getSize()));
        deck2.textProperty().bind(deck2Count);

        // TODO tambahin turn ke deck1 deck2
        deck1.setOnMouseReleased(e -> {
            if (phase == Phase.DRAW_PHASE) player1.getDeck().doDraw();
        });
        deck2.setOnMouseReleased(e -> {
            if (phase == Phase.DRAW_PHASE) player2.getDeck().doDraw();
        });

        hand1Pane.setOnScroll(event -> {
            if (event.getDeltaX() == 0 && event.getDeltaY() != 0)
                hand1Pane.setHvalue(hand1Pane.getHvalue() - event.getDeltaY()*1.5 / this.hand1Pane.getWidth());
        });
        hand2Pane.setOnScroll(event -> {
            if (event.getDeltaX() == 0 && event.getDeltaY() != 0)
                hand2Pane.setHvalue(hand2Pane.getHvalue() - event.getDeltaY()*1.5 / this.hand2Pane.getWidth());
        });

    }

    public MainPageController(GameplayChannel channel, int cardAmount, String P1, String P2) {
        // % Gameplay Channel
        this.channel = channel;
        this.channel.addSubscriber("CHANGE_PHASE", this);
        this.channel.addSubscriber("CHANGE_PLAYER", this);
        this.channel.addSubscriber("DISPLAY_CARD", this);
        this.channel.addSubscriber("DRAW_EVENT", this);

        this.cardAmount = cardAmount;
        this.player1 = new Player(P1, 80, channel);
        this.player2 = new Player(P2, 80, channel);
        Character card = new Character("Aang", Element.AIR, "Aang pemuda avatar", "com/avatarduel/card/image/character/Aang.png", 1, 1, 1);
        Aura card2 = new Aura("Shozin Comet", Element.FIRE, "Komet sing edan", "com/avatarduel/card/image/skill/Shozin Comet.png", 1, 1, 1);
        this.display = card;
        this.display2 = card2;
        this.phase = Phase.GAME_INIT;
        this.turn = 1;
        this.channel.activePlayer = player1.getName();
    }

    public String getNextPlayer() {
        if (turn%2 == 1) {
            return player1.getName();
        }
        else {
            return player2.getName();
        }
    }

    public void setPhase(Phase p) {this.phase = p;}
    public Label getPhaseBox() {
        switch (this.phase) {
            case DRAW_PHASE: return this.drawPhaseBox;
            case MAIN_PHASE: return this.mainPhaseBox;
            case BATTLE_PHASE: return this.battlePhaseBox;
            default: return this.endPhaseBox;
        }
    }

    public Phase getNextPhase() {
        int idx = this.phase.ordinal();
        int nextIdx = idx + 1;
        Phase[] phases = Phase.values();
        nextIdx %= phases.length;
        if (nextIdx == 0) nextIdx++;
        return phases[nextIdx];
    }

    public void doChangePhase() {
        Phase p = this.phase;
        try {
            getPhaseBox().getStylesheets().clear();
            getPhaseBox().getStylesheets().add(PHASE_STYLE_PATH);
        } catch (Exception e) {
            System.out.println("Stylesheet failed to load!");
            System.out.println("Error = " + e);
        }
        p = this.getNextPhase();
        ChangePhaseEvent e = new ChangePhaseEvent(p);
        this.publish("CHANGE_PHASE", e);
        e.execute();
//        if (p == Phase.DRAW_PHASE) {
//            doChangeTurn();
//        }
    }

    public void doDraw() {

    }


    @Override
    public void publish(String topic, BaseEvent event) {this.channel.sendEvent(topic, event);}

    public void onChangePhase(ChangePhaseEvent e) {
        setPhase(e.phase);
        getPhaseBox().getStylesheets().clear();
        getPhaseBox().getStylesheets().add(CUR_PHASE_STYLE_PATH);
        this.cardPane.getChildren().clear();
        if (e.phase == Phase.DRAW_PHASE) {
            AlertPlayer alert = new AlertPlayer(channel.activePlayer + "'s Turn!", AlertType.INFORMATION, "Info Turn " + turn);
            alert.show();
        }
        if (e.phase == Phase.END_PHASE) {
            turn++;
            this.publish("CHANGE_PLAYER", new ChangePlayerEvent(getNextPlayer()));
        }
    }

    public void onDisplayCard(DisplayCardEvent event) {
        this.cardPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(CARD_FXML_PATH));
        loader.setControllerFactory(c -> new CardDisplay(this.channel, event.card.getCard(), 400, 560));
        try {
            this.cardPane.getChildren().add(loader.load());
        } catch (Exception e) {
            System.out.println("Failed to load card to cardPane!");
            System.out.println("Error = " + e);
        }
    }

    public void onDrawEvent(DrawEvent event) {
        if (this.phase != Phase.GAME_INIT) {
            if (event.h == this.player1.getName()) {
                this.deck1Count.setValue(Integer.toString(player1.getDeck().getSize()));
            } else if (event.h == this.player2.getName()) {
                this.deck2Count.setValue(Integer.toString(player2.getDeck().getSize()));
            }
            doChangePhase();
        }
    }

    @Override
    public void onChangePlayer(ChangePlayerEvent e) {
        this.channel.activePlayer = e.nextPlayer;
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof ChangePhaseEvent) {
            this.onChangePhase((ChangePhaseEvent) event);
        } else if (event instanceof DisplayCardEvent) {
            this.onDisplayCard((DisplayCardEvent) event);
        } else if (event instanceof DrawEvent) {
            this.onDrawEvent((DrawEvent) event);
        } else if (event instanceof ChangePlayerEvent) {
            this.onChangePlayer((ChangePlayerEvent) event);
        }
    }
}