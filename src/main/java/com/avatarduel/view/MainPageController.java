package com.avatarduel.view;

import com.avatarduel.model.Element;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Aura;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.player.Power;
import com.avatarduel.view.cards.CardDisplay;
//import com.avatarduel.view.cards.CharDisplay;
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
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private Pane cardPane;
    @FXML
    public Label name1;
    @FXML
    public Label health1;
    @FXML
    public Label name2;
    @FXML
    public Label health2;
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

    private Player player1;
    private Player player2;
    private Card display;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/com/avatarduel/fxml/CardDisplay.fxml"));
        cardLoader.setControllerFactory(c -> new CardDisplay(this.channel, display));
        try {
            this.cardPane.getChildren().add(cardLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        this.name1.setText("abcdefghijkl");

    }

    public MainPageController(GameplayChannel channel, int cardAmount, String P1, String P2) {
        this.channel = channel;
        this.cardAmount = cardAmount;
        this.player1 = new Player(P1, 80, channel);
        this.player2 = new Player(P2, 80, channel);
        Character card = new Character("Aang", Element.AIR, "Aang pemuda avatar", "com/avatarduel/card/image/character/Aang.png", 1, 1, 1);
//        Aura card = new Aura("Shozin Comet", Element.FIRE, "Komet sing edan", "com/avatarduel/card/image/skill/Shozin Comet.png", 1, 1, 1);
        this.display = card;






//        this.player1 = new Player()


//        //stage.setFullScreen(true);
//        Player p1 = new Player("Hojun", 80, channel);
//        Character card = new Character("Aang", Element.AIR,"Aang is the last surviving Airbender, a monk of the Air Nomads' Southern Air Temple. He is an incarnation of the \"Avatar\", the spirit of light and peace manifested in human form.","com/avatarduel/card/image/character/Aang.png",100,100,100);
//        SummonedCharacter cardS = new SummonedCharacter(card, false, new Player("hengky",80, channel), channel);
//        try {
//
//        }
//        CardDisplay DCard = new CharDisplay(cardS.getCharCard(), cardPane, cardDisW, cardDisH, cardDisPosX, cardDisPosY);
//
//        Power powlist = new Power(gameplay,"hengky");
//        PowerDisplay P = new PowerDisplay(powlist,pane);


    }

}