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
import java.util.ResourceBundle;

public class BoardDisplay implements BaseView, Initializable, Publisher, Subscriber {

    @FXML
    public AnchorPane card0;
    @FXML
    public AnchorPane card1;
    @FXML
    public AnchorPane card2;
    @FXML
    public AnchorPane card3;
    @FXML
    public AnchorPane card4;
    @FXML
    public AnchorPane card5;
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

    private GameplayChannel channel
    private Board board;
    private CardDisplay[] arrCharCD;
    private CardDisplay[] arrSkillCD;
    private double boardW;
    private double boardH;


    public BoardDisplay(GameplayChannel gameplayChannel, Board B) {
        this.channel = gameplayChannel;
        this.board = B;
        this.arrCharCD = new CardDisplay[6];
        this.arrSkillCD = new CardDisplay[6];
        for (int i = 0; i < 6; i++) {
            arrCharCD[i] = null;
            arrSkillCD[i] = null;
        }
        channel.addSubscriber("SUMMON_CHARACTER", this);
        channel.addSubscriber("SUMMON_SKILL", this);
        channel.addSubscriber("DISCARD_SKILL", this);
        channel.addSubscriber("DESTROY_CHARACTER_EVENT", this);
    }

    public void doIndicateSlot() {
        for (int i = 0; i < 6; i++) {
            if (arrCharCD[i] != null) {

            }
        }
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Board getBoard() {return this.board;}

    @Override
    public void publish(String topic, BaseEvent event) {

    }

    @Override
    public void onEvent(BaseEvent event) {

    }


}
