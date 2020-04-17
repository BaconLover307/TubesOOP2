package com.avatarduel.view;

import com.avatarduel.model.Element;
import com.avatarduel.model.Phase;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Aura;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.ChangePhaseEvent;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.player.Power;
import com.avatarduel.view.cards.CardDisplay;
//import com.avatarduel.view.cards.CharDisplay;
import com.avatarduel.util.CSVReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

import javax.naming.Name;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MainPageController implements Initializable, Publisher, Subscriber,
        ChangePhaseEvent.ChangePhaseEventHandler {
    private static final String CHAR_CSV_FILE_PATH = "../card/data/character.csv";
    private static final String LAND_CSV_FILE_PATH = "../card/data/land.csv";
    private static final String AURA_CSV_FILE_PATH = "../card/data/skill_aura.csv";

    @FXML
    private Pane cardPane;
    @FXML
    public Label name1;
    @FXML
    public Label health1;
    @FXML
    public Label deck1;
    @FXML
    public Label name2;
    @FXML
    public Label health2;
    @FXML
    public Label deck2;
    @FXML
    public ScrollPane hand1Pane;
    @FXML
    public HBox hand1HBox;
    @FXML
    public ScrollPane hand2Pane;
    @FXML
    public HBox hand2HBox;
    @FXML
    public AnchorPane board1Pane;
    @FXML
    public AnchorPane board2Pane;
    @FXML
    public Pane power1Pane;
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
    private int turn;
    private Phase phase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // * Loads the CSV
        try {
            File charCSVFile = new File(getClass().getResource(CHAR_CSV_FILE_PATH).toURI());
            File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
            File auraCSVFile = new File(getClass().getResource(AURA_CSV_FILE_PATH).toURI());
            this.player1.getDeck().loadDeck(charCSVFile, auraCSVFile, landCSVFile);
            this.player2.getDeck().loadDeck(charCSVFile, auraCSVFile, landCSVFile);
        } catch (Exception e) {
            System.out.println("Failed to load CSV Files!");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }

        // ! Temporary Deck Loader
        Random objGen = new Random();
//        for (int i=0; i<30; i++) {
//            int rand = objGen.nextInt(charCardList.size());
//            this.player1.getDeck().addCharFromArr(charCardList.get(rand));
//            rand = objGen.nextInt(charCardList.size());
//            this.player2.getDeck().addCharFromArr(charCardList.get(rand));
//        }
//
//        for (int i=0; i<15; i++) {
//            int rand = objGen.nextInt(landCardList.size());
//            this.player1.getDeck().addLandFromArr(landCardList.get(rand));
//            rand = objGen.nextInt(auraCardList.size());
//            this.player1.getDeck().addAuraFromArr(auraCardList.get(rand));
//            rand = objGen.nextInt(landCardList.size());
//            this.player2.getDeck().addLandFromArr(charCardList.get(rand));
//            rand = objGen.nextInt(auraCardList.size());
//            this.player2.getDeck().addAuraFromArr(auraCardList.get(rand));
//        }
        player1.getDeck().shuffle();
        player2.getDeck().shuffle();


        FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/com/avatarduel/fxml/CardDisplay.fxml"));
        cardLoader.setControllerFactory(c -> new CardDisplay(this.channel, display));
        try {
            this.cardPane.getChildren().add(cardLoader.load());
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }
//        this.name1.setText("abcdefghijkl");

    }

    public MainPageController(GameplayChannel channel, int cardAmount, String P1, String P2) {
        this.channel = channel;
        this.cardAmount = cardAmount;
        this.player1 = new Player(P1, 80, channel);
        this.player2 = new Player(P2, 80, channel);
//        Character card = new Character("Aang", Element.AIR, "Aang pemuda avatar", "com/avatarduel/card/image/character/Aang.png", 1, 1, 1);
//        Aura card = new Aura("Shozin Comet", Element.FIRE, "Komet sing edan", "com/avatarduel/card/image/skill/Shozin Comet.png", 1, 1, 1);
//        this.display = card;
        this.phase = Phase.GAME_INIT;
        this.turn = 1;
    }

    public void setPhase(Phase p) {this.phase = p;}

    public Phase getNextPhase() {
        int idx = this.phase.ordinal();
        int nextIdx = idx + 1;
        Phase[] phases = Phase.values();
        nextIdx %= phases.length;
        if (nextIdx == 0) nextIdx++;
        return phases[nextIdx];
    }

    public void doChangePhase() {
        Phase p = this.getNextPhase();
        this.publish("CHANGE_PHASE", new ChangePhaseEvent(p));
    }


    @Override
    public void publish(String topic, BaseEvent event) {this.channel.sendEvent(topic, event);}

    public void onChangePhase(ChangePhaseEvent e) {
        this.phase = e.phase;
    }

    @Override
    public void onEvent(BaseEvent event) {

    }
}