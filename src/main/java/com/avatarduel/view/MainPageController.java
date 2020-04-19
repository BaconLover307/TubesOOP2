package com.avatarduel.view;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.Element;
import com.avatarduel.model.Phase;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Land;
import com.avatarduel.model.cards.card.Aura;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.*;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable, Publisher, Subscriber,
        ChangePhaseEvent.ChangePhaseEventHandler,
        DisplayCardEvent.DisplayCardEventHandler,
        UseLandEvent.UseLandEventHandler,
        RequestSummonEvent.RequestSummonEventHandler,
        ChangePlayerEvent.ChangePlayerEventHandler,
        EndGameEvent.EndGameEventHandler {
    private static final String CHAR_CSV_FILE_PATH = "../card/data/character.csv";
    private static final String LAND_CSV_FILE_PATH = "../card/data/land.csv";
    private static final String AURA_CSV_FILE_PATH = "../card/data/skill_aura.csv";
    private static final String CARD_FXML_PATH = "../fxml/CardDisplay.fxml";
    private static final String BOARD1_FXML_PATH = "../fxml/Board1Display.fxml";
    private static final String BOARD2_FXML_PATH = "../fxml/Board2Display.fxml";
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
    private StringProperty health1Text;
    @FXML
    public Label health1;
    private StringProperty deck1Count;
    @FXML
    public Label deck1;

    @FXML
    public Label name2;
    private StringProperty health2Text;
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

    public BoardDisplay board1;
    @FXML
    public AnchorPane board1Pane;
    public BoardDisplay board2;
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
    private boolean isSelecting;

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

        // Name and Health Setup
        this.name1.setText(player1.getName());
        this.health1Text = new SimpleStringProperty(Integer.toString(player1.getHealth()));
        this.health1.textProperty().bind(health1Text);

        this.name2.setText(player2.getName());
        this.health2Text = new SimpleStringProperty(Integer.toString(player2.getHealth()));
        this.health2.textProperty().bind(health2Text);


        // Hand Display Setup
        this.hand1Dis = new HandDisplay(this.channel, player1.getHand());
        this.hand1HBox.getChildren().addAll(this.hand1Dis.getHandBox());
        this.hand2Dis = new HandDisplay(this.channel, player2.getHand());
        this.hand2HBox.getChildren().addAll(this.hand2Dis.getHandBox());
        for (int i = 0; i<7; i++) {
            this.player1.getDeck().doDraw();
            this.player2.getDeck().doDraw();
        }
        this.hand1Dis.flipOpen();
        this.hand2Dis.flipClose();

        // Deck setup
        this.deck1Count = new SimpleStringProperty(Integer.toString(player1.getDeck().getSize()));
        deck1.textProperty().bind(deck1Count);
        this.deck2Count = new SimpleStringProperty(Integer.toString(player2.getDeck().getSize()));
        deck2.textProperty().bind(deck2Count);

        // Board setup
        this.board1 = new BoardDisplay(this.channel, this.player1.getBoard());
        try {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource(BOARD1_FXML_PATH));
            loader1.setControllerFactory(c -> this.board1);
            this.board1Pane.getChildren().add(loader1.load());
        } catch (Exception e) {
            System.out.println("Board1 failed to load!");
            e.printStackTrace();
        }


        this.board2 = new BoardDisplay(this.channel, this.player2.getBoard());
        try {
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource(BOARD2_FXML_PATH));
            loader2.setControllerFactory(c -> this.board2);
            this.board2Pane.getChildren().add(loader2.load());
        } catch (Exception e) {
            System.out.println("Board2 failed to load!");
            e.printStackTrace();
        }

        // Power Display Setup
        power1Dis = new PowerDisplay(this.channel, player1.getPowers());
        this.power1Pane.getChildren().add(power1Dis.getPane());
        power2Dis = new PowerDisplay(this.channel, player2.getPowers());
        this.power2Pane.getChildren().add(power2Dis.getPane());

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

        // ACTION SETUP
        deck1.setOnMouseReleased(e -> {
            if (getPhase() == Phase.DRAW_PHASE && turn == 1) player1.getDeck().doDraw();
        });
        deck2.setOnMouseReleased(e -> {
            if (getPhase() == Phase.DRAW_PHASE && turn == 2) player2.getDeck().doDraw();
        });

        hand1Pane.setOnScroll(event -> {
            if (event.getDeltaX() == 0 && event.getDeltaY() != 0 && turn == 1)
                hand1Pane.setHvalue(hand1Pane.getHvalue() - event.getDeltaY()*1.5 / this.hand1Pane.getWidth());
        });
        hand2Pane.setOnScroll(event -> {
            if (event.getDeltaX() == 0 && event.getDeltaY() != 0 && turn == 2)
                hand2Pane.setHvalue(hand2Pane.getHvalue() - event.getDeltaY()*1.5 / this.hand2Pane.getWidth());
        });

        // ? Change Here if want to enable continue without draw
        btnNext.setOnAction(event -> {
            if (getPhase() != Phase.DRAW_PHASE && !this.channel.isSelecting) doChangePhase();
        });

        this.root.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                this.channel.isSelecting = false;
                this.board1.ResetAll();
                this.board2.ResetAll();
            }
        });

        // START GAME
        AlertPlayer gameStartAlert = new AlertPlayer("Start Game! " + channel.activePlayer.getName() + "'s Turn!", AlertType.INFORMATION, "Start Game");
        gameStartAlert.show();
        doChangePhase();

        // DEBUG
        this.hand1Dis.addCard(display);
        this.hand1Dis.addCard(display);
        this.hand1Dis.addCard(display2);
        this.hand1Dis.addCard(display2);
        this.hand1Dis.addCard(display2);
    }

    public MainPageController(GameplayChannel channel, int cardAmount, String P1, String P2) {
        // % Gameplay Channel
        this.channel = channel;
        this.channel.addSubscriber("CHANGE_PHASE", this);
        this.channel.addSubscriber("CHANGE_PLAYER", this);
        this.channel.addSubscriber("DISPLAY_CARD", this);
        this.channel.addSubscriber("DRAW_EVENT", this);
        this.channel.addSubscriber("END_GAME", this);
        this.channel.addSubscriber("USE_LAND", this);
        this.channel.addSubscriber("REQUEST_SUMMON", this);

        this.cardAmount = cardAmount;
        this.player1 = new Player(P1, 80, channel);
        this.player2 = new Player(P2, 80, channel);
        Character card = new Character("Aang", Element.AIR, "Aang pemuda avatar", "com/avatarduel/card/image/character/Aang.png", 1, 1, 1);
        Land card2 = new Land("AIR apalah", Element.AIR, "Udara", "com/avatarduel/card/image/skill/Shozin Comet.png");
        this.display = card;
        this.display2 = card2;
        setPhase(Phase.GAME_INIT);
        this.turn = 1;
        this.channel.activePlayer = player1;

    }

    public Player getNextPlayer() {
        if (turn == 2) return player1;
        else return player2;
    }

    public void setPhase(Phase p) {this.channel.phase = p;}
    public Phase getPhase() {return this.channel.phase;}

    public Label getPhaseBox() {
        switch (getPhase()) {
            case DRAW_PHASE: return this.drawPhaseBox;
            case MAIN_PHASE: return this.mainPhaseBox;
            case BATTLE_PHASE: return this.battlePhaseBox;
            case END_PHASE: return this.endPhaseBox;
            default: return null;
        }
    }

    public Phase getNextPhase() {
        int idx = getPhase().ordinal();
        int nextIdx = idx + 1;
        Phase[] phases = Phase.values();
        nextIdx %= phases.length;
        if (nextIdx == 0) nextIdx++;
        return phases[nextIdx];
    }

    // PUBLISH
    public void doChangePhase() {
        Phase p;
        try {
            if (getPhase() != Phase.GAME_INIT) {
                getPhaseBox().getStylesheets().clear();
                getPhaseBox().getStylesheets().add(PHASE_STYLE_PATH);
            }
        } catch (Exception e) {
            System.out.println("Change Phase: Stylesheet failed to load!");
            System.out.println("Error = " + e);
        }
        p = this.getNextPhase();
        ChangePhaseEvent e = new ChangePhaseEvent(p);
        this.publish("CHANGE_PHASE", e);
        e.execute();
    }

    @Override
    public void publish(String topic, BaseEvent event) {this.channel.sendEvent(topic, event);}

    // ONEVENT
    public void onChangePhase(ChangePhaseEvent e) {
        setPhase(e.phase);
        if (getPhase() != Phase.GAME_INIT) {
            getPhaseBox().getStylesheets().clear();
            getPhaseBox().getStylesheets().add(CUR_PHASE_STYLE_PATH);
        }
        this.cardPane.getChildren().clear();
        if (getPhase() == Phase.DRAW_PHASE) {
            this.publish("RESET_POWER_EVENT", new ResetPowerEvent(this.channel.activePlayer.getName()));
        }
        // else if (getPhase() == Phase.MAIN_PHASE) {
        //     System.out.println("P1 HAND: " + this.player1.getHand().getSize());
        //     System.out.println("P2 HAND: " + this.player2.getHand().getSize());
        // }
        else if (getPhase() == Phase.END_PHASE) {
            Player nextPlayer = getNextPlayer();
            AlertPlayer alert = new AlertPlayer(nextPlayer.getName() + "'s Turn!", AlertType.INFORMATION, "Info Turn " + (turn % 2 + 1));
            alert.show();
            this.publish("CHANGE_PLAYER", new ChangePlayerEvent(nextPlayer));
            doChangePhase();
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
        if (getPhase() != Phase.GAME_INIT) {
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
        this.turn = turn%2+1;
        if (this.channel.activePlayer.equals(player1)) {
            hand1Dis.flipOpen();
            hand2Dis.flipClose();
        } else {
            hand2Dis.flipOpen();
            hand1Dis.flipClose();
        }
    }

    @Override
    public void onRequestSummon(RequestSummonEvent e) {
        if (this.channel.activePlayer.equals(e.owner)) {
            if (e.card instanceof Character) {
            }
        }
    }

    @Override
    public void onEndGame(EndGameEvent e) {
        e.execute(); 
    }

    @Override
    public void onUseLandEvent(UseLandEvent e) {
        this.power1Dis.updatePower();
        this.power2Dis.updatePower();
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
        } else if (event instanceof UseLandEvent) {
            this.onUseLandEvent((UseLandEvent) event);
        } else if (event instanceof RequestSummonEvent) {
            this.onRequestSummon((RequestSummonEvent) event);
        } else if (event instanceof EndGameEvent) {
            this.onEndGame((EndGameEvent) event);
        }
    }
}