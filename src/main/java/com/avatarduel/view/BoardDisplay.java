package com.avatarduel.view;

import com.avatarduel.model.Phase;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.cards.cardcollection.Board;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.RequestSummonEvent;
import com.avatarduel.model.gameplay.events.SpendPowerEvent;
import com.avatarduel.model.gameplay.events.SummonCharacterEvent;
import com.avatarduel.view.cards.CardDisplay;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardDisplay implements BaseView, Initializable, Publisher, Subscriber,
        RequestSummonEvent.RequestSummonEventHandler,
        SummonCharacterEvent.SummonCharacterEventHandler{

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
        this.channel.addSubscriber("REQUEST_SUMMON", this);

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

    public Board getBoard() {return this.board;}

    public void addChartoBoard(int id, Character card) {
        CardDisplay cD = new CardDisplay(this.channel, card, CARD_SIZEW, CARD_SIZEH);
        SummonedCharacter SC = new SummonedCharacter(card, true, channel.activePlayer.getName(), channel);
        arrCharCD[id] = cD;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/CardDisplay.fxml"));
            loader.setControllerFactory(c -> cD);
            Pane toLoad = loader.load();
            this.arrCharPane[id].getChildren().add(toLoad);
            this.arrCharPane[id].setOnMouseClicked(event -> {
                if (this.channel.activePlayer.getName() == this.board.getOwner() &&
                        !this.channel.isSelecting) {
                    if (this.channel.phase == Phase.MAIN_PHASE) rotateChar(cD);
                    if (this.channel.phase == Phase.BATTLE_PHASE) attackChar(cD);
                }
            });


        } catch (Exception e) {
            System.out.println("Board failed to summon Character!");
            e.printStackTrace();
        }

    }

    public void rotateChar(CardDisplay cD) {
        System.out.println("ROTATING CARD");
    }

    public void attackChar(CardDisplay cD) {
        System.out.println("ATTACK CARD");
    }

    public void ResetOnAction(AnchorPane[] arrPane) {
        for (int i=0; i<arrPane.length; i++) {
            arrPane[i].setOnMouseClicked(null);
            arrPane[i].setStyle(null);
        }
    }

    public void ResetAll() {
        for (int i=0; i<6; i++) {
            arrCharPane[i].setOnMouseClicked(null);
            arrCharPane[i].setStyle(null);
            arrSkillPane[i].setOnMouseClicked(null);
            arrSkillPane[i].setStyle(null);
        }
    }

//    public ArrayList<AnchorPane> getSelectableChar(int size) {
//        ArrayList<AnchorPane> idList = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            if (arrCharPane[i].getChildren().size() == size) {
//                idList.add(arrCharPane[i]);
//            }
//        }
//        return idList;
//    }

    public void doSelectCharSlotAvailable(RequestSummonEvent event) {
        for (int i = 0; i < 6; i++) {
            if (arrCharPane[i].getChildren().size() == 0) {
                arrCharPane[i].setStyle(
                        "-fx-border-radius: 0.5em;" +
                        "-fx-border-width: 0.5em;"+
                        "-fx-border-color: green"
                );
                int finalI = i;
                arrCharPane[i].setOnMouseClicked(e -> {
                    publish("SUMMON_CHARACTER", new SummonCharacterEvent((Character) event.card, finalI, event.owner));
                    publish("SPEND_POWER_EVENT", new SpendPowerEvent(event.owner, event.card.getElement(), ((Character)(event.card)).getPower()));
                    ResetAll();
                });
            }
        }
    }

    @Override
    public void publish(String topic, BaseEvent event) {this.channel.sendEvent(topic, event);}

    @Override
    public void onRequestSummon(RequestSummonEvent e) {
        if (e.owner == this.board.getOwner()) {
            if (e.card instanceof Character) {
                doSelectCharSlotAvailable(e);
            }
        }
    }

    @Override
    public void onSummonCharacterEvent(SummonCharacterEvent e) {
        if (e.owner == this.board.getOwner()) {
            addChartoBoard(e.id, e.C);
            this.channel.isSelecting = false;
        }
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof RequestSummonEvent) {
            this.onRequestSummon((RequestSummonEvent) event);
        } else if (event instanceof SummonCharacterEvent) {
            this.onSummonCharacterEvent((SummonCharacterEvent) event);
        }
    }


}
