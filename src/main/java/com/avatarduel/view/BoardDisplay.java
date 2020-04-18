package com.avatarduel.view;

import com.avatarduel.model.cards.cardcollection.Board;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.view.cards.CardDisplay;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BoardDisplay implements BaseView, Initializable, Publisher, Subscriber {

    @FXML
    public AnchorPane char0;
    @FXML
    public AnchorPane char1;
    @FXML
    public AnchorPane char2;
    @FXML
    public AnchorPane char3;
    @FXML
    public AnchorPane char4;
    @FXML
    public AnchorPane char5;
    @FXML
    public AnchorPane skill0;
    @FXML
    public AnchorPane skill1;
    @FXML
    public AnchorPane skill2;
    @FXML
    public AnchorPane skill3;
    @FXML
    public AnchorPane skill4;
    @FXML
    public AnchorPane skill5;

    private GameplayChannel channel;
    private Board board;
    private CardDisplay[] arrCharCD;
    private AnchorPane[] arrCharPane;
    private CardDisplay[] arrSkillCD;
    private AnchorPane[] arrSkillPane;
    private double boardW;
    private double boardH;


    public BoardDisplay(GameplayChannel gameplayChannel, Board B) {
        this.channel = gameplayChannel;
        this.board = B;
        this.arrCharCD = new CardDisplay[6];
        this.arrSkillCD = new CardDisplay[6];
        this.arrCharPane = new AnchorPane[6];
        this.arrSkillPane = new AnchorPane[6];
        for (int i = 0; i < 6; i++) {
            arrCharCD[i] = null;
            arrSkillCD[i] = null;
        }
        channel.addSubscriber("SUMMON_CHARACTER", this);
        channel.addSubscriber("SUMMON_SKILL", this);
        channel.addSubscriber("DISCARD_SKILL", this);
        channel.addSubscriber("DESTROY_CHARACTER_EVENT", this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.arrCharPane[0] = char0;
        this.arrCharPane[1] = char1;
        this.arrCharPane[2] = char2;
        this.arrCharPane[3] = char3;
        this.arrCharPane[4] = char4;
        this.arrCharPane[5] = char5;
        this.arrSkillPane[0] = skill0;
        this.arrSkillPane[1] = skill1;
        this.arrSkillPane[2] = skill2;
        this.arrSkillPane[3] = skill3;
        this.arrSkillPane[4] = skill4;
        this.arrSkillPane[5] = skill5;
    }

    public void doIndicateCharSlotAvailable() {
        for (int i = 0; i < 6; i++) {
            if (arrCharPane[i].getChildren() == null) {
                arrCharPane[i].setStyle(
                        "-fx-border-radius: 1em;" +
                        "-fx-border-width: 0.5em;"+
                        "-fx-border-color: green"
                );
            }
        }
    }

    public void selectedSlot() {

    }

//        this.boardW = boardW;
//        this.boardH = boardH;
//        Pane board = new Pane();
//        board.setPrefSize(boardW,boardH);
//        board.relocate(posX, posY);
//        Image img = new Image("com/avatarduel/asset/card-character.png");
//        BackgroundSize backgroundSize = new BackgroundSize(cardW, cardH, false, false, false, false);
//        BackgroundImage backgroundImage = new BackgroundImage(img,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.DEFAULT,
//                backgroundSize);
//        Background background = new Background(backgroundImage);
//        box.setBackground(background);
//
//        nameSz = 0.075 * cardH;
//        nameX = 0.0875 * cardW;
//        nameY = 0.11 * cardH;
//        name = new Text();
//        name.setText(C.getName());
//        name.setFont(Font.font(java.awt.Font.SERIF, nameSz));
//        name.setX(nameX);

//        name.setY(nameY);


    public Board getBoard() {return this.board;}

    @Override
    public void publish(String topic, BaseEvent event) {

    }

    @Override
    public void onEvent(BaseEvent event) {

    }


}
