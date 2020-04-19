package com.avatarduel.view;

import com.avatarduel.model.Phase;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.cards.cardcollection.Board;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardDisplay implements BaseView, Initializable, Publisher, Subscriber,
        RequestSummonEvent.RequestSummonEventHandler,
        SummonCharacterEvent.SummonCharacterEventHandler,
        SummonSkillEvent.SummonSkillEventHandler,
        SelectEnemyEvent.SelectEnemyEventHandler,
        RequestSkillTargetEvent.RequestSkillTargetEventHandler {

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
        this.channel.addSubscriber("REQUEST_SKILL_TARGET", this);
        this.channel.addSubscriber("SUMMON_CHARACTER", this);
        this.channel.addSubscriber("SUMMON_SKILL", this);
        this.channel.addSubscriber("DISCARD_SKILL", this);
        this.channel.addSubscriber("DESTROY_CHARACTER_EVENT", this);

        this.board = B;
        this.arrCharCD = new CardDisplay[6];
        this.arrSkillCD = new CardDisplay[6];
        this.arrCharPane = new AnchorPane[6];
        this.arrSkillPane = new AnchorPane[6];
        for (int i = 0; i < 6; i++) {
            arrCharCD[i] = null;
            arrSkillCD[i] = null;
        }

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
                    if (this.channel.phase == Phase.MAIN_PHASE) rotateChar(SC, this.arrCharPane[id]);
                    if (this.channel.phase == Phase.BATTLE_PHASE) attackChar(SC, this.arrCharPane[id]);
                }
            });
        } catch (Exception e) {
            System.out.println("Board failed to summon Character!");
            e.printStackTrace();
        }

    }

    public void addSkilltoBoard(int id, Skill card, int Cid) {
        CardDisplay cD = new CardDisplay(this.channel, card, CARD_SIZEW, CARD_SIZEH);
//        SummonedCharacter SC = new SummonedCharacter(card, true, channel.activePlayer.getName(), channel);
        arrSkillCD[id] = cD;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/CardDisplay.fxml"));
            loader.setControllerFactory(c -> cD);
            Pane toLoad = loader.load();
            this.arrSkillPane[id].getChildren().add(toLoad);

        } catch (Exception e) {
            System.out.println("Board failed to summon Character!");
            e.printStackTrace();
        }

    }

    public void rotateChar(SummonedCharacter SC, AnchorPane AP) {
        System.out.println("ROTATING CARD");
        String changePos;
        if (SC.getPosition()) changePos = "Defend";
        else changePos = "Attack";
        AlertChoice rotateChoice = new AlertChoice(changePos, "", ("Summoned Character " + SC.getCharCard().getName() + " selected. Change Position?"), "Change Position");
        String ret = rotateChoice.showAndReturn();
        if (ret.equals(changePos)) {
            publish("REPOSITION_CHARACTER", new RepositionCharacterEvent(SC, this.board.getOwner()));
            if (ret.equals("Attack")) AP.setRotate(0);
            else AP.setRotate(90);
            System.out.println("Changed card position");
        }
    }

    public void attackChar(SummonedCharacter SC, AnchorPane AP) {
        if (!SC.getPosition()) {
            AlertPlayer isDef = new AlertPlayer("Your character is in Defense Position!", Alert.AlertType.WARNING, "Defensive Character");
            isDef.show();
        } else {
            AlertChoice SC_Choice = new AlertChoice("Yes", "", (SC.getCharCard().getName() + " selected. Attack enemy?"), "Battle");
            String ret = SC_Choice.showAndReturn();
            if (ret.equals("Yes")) {
                AP.setStyle(
                        "-fx-border-radius: 0.5em;" +
                        "-fx-border-width: 0.5em;"+
                        "-fx-border-color: orange"
                );
                publish("SELECT_ENEMY", new SelectEnemyEvent(SC));
                this.channel.isSelecting = true;
                System.out.println("ATTACK CARD");
            }
        }
    }

    public void discardSkill(Skill S, AnchorPane AP) {

    }

    public void ResetStyle() {
        for (int id=0; id<6; id++) {
            arrCharPane[id].setStyle(null);
            arrSkillPane[id].setStyle(null);
        }
    }

    public void ResetBoardProperty() {
        boolean[] charAvail = getBoard().getAvailableCharSlot();
        boolean[] skillAvail = getBoard().getAvailableSkillSlot();
        for (int id=0; id<6; id++) {
            arrCharPane[id].setStyle(null);
            arrCharPane[id].setOnMouseClicked(null);
            if (!charAvail[id]) {
                int i = id;
                this.arrCharPane[id].setOnMouseClicked(event -> {
                    if (this.channel.activePlayer.getName() == this.board.getOwner() &&
                            !this.channel.isSelecting) {
                        if (this.channel.phase == Phase.MAIN_PHASE) {
                            rotateChar(getBoard().getCharwithId(i), this.arrCharPane[i]);
                        }
                        if (this.channel.phase == Phase.BATTLE_PHASE) {
                            attackChar(getBoard().getCharwithId(i), this.arrCharPane[i]);
                        }
                    }
                });
            }
            arrSkillPane[id].setStyle(null);
            arrSkillPane[id].setOnMouseClicked(null);
            if (!skillAvail[id]) {
                int i = id;
                this.arrSkillPane[id].setOnMouseClicked(event -> {
                    if (this.channel.activePlayer.getName() == this.board.getOwner() &&
                            !this.channel.isSelecting) {
                        if (this.channel.phase == Phase.MAIN_PHASE)
                            discardSkill(this.getBoard().getSkillwithId(i), this.arrCharPane[i]);
                    }
                });
            }
        }
    }


    public void doSelectCharSlotAvailable(RequestSummonEvent event) {
        for (int i = 0; i < 6; i++) {
            if (arrCharPane[i].getChildren().size() == 0) {
                arrCharPane[i].setStyle(
                        "-fx-border-radius: 0.5em;" +
                        "-fx-border-width: 0.5em;"+
                        "-fx-border-color: green"
                );
                int ID = i;
                arrCharPane[i].setOnMouseClicked(e -> {
                    publish("SUMMON_CHARACTER", new SummonCharacterEvent((Character) event.card, ID, event.owner));
                    publish("SPEND_POWER_EVENT", new SpendPowerEvent(event.owner, event.card.getElement(), ((Character)(event.card)).getPowVal()));
//                    ResetBoardProperty();  -> GA PERLU, UDAH DI MAINCONT
                });
            }
        }
    }
    public void doSelectSkillSlotAvailable(RequestSummonEvent event) {
        for (int i = 0; i < 6; i++) {
            if (arrSkillPane[i].getChildren().size() == 0) {
                arrSkillPane[i].setStyle(
                        "-fx-border-radius: 0.5em;" +
                        "-fx-border-width: 0.5em;"+
                        "-fx-border-color: deepskyblue"
                );
                int ID = i;
                arrSkillPane[i].setOnMouseClicked(e -> {
                    ResetStyle();
                    publish("REQUEST_SKILL_TARGET", new RequestSkillTargetEvent((Skill) event.card, ID, event.owner));
                });
            }
        }
    }

    @Override
    public void publish(String topic, BaseEvent event) {this.channel.sendEvent(topic, event);}

    @Override
    public void onRequestSummon(RequestSummonEvent e) {
        if (e.owner.equals(this.board.getOwner())) {
            if (e.card instanceof Character) {
                doSelectCharSlotAvailable(e);
            }
        }
    }

    @Override
    public void onSummonCharacterEvent(SummonCharacterEvent e) {
        if (e.owner.equals(this.board.getOwner())) {
            addChartoBoard(e.id, e.C);
            this.channel.isSelecting = false;
        }
    }

    @Override
    public void onSummonSkillEvent(SummonSkillEvent e) {
        if (e.owner.equals(this.board.getOwner())) {
            addSkilltoBoard(e.Sid, e.S, e.Cid);
            this.channel.isSelecting = false;
        }
    }

    @Override
    public void onRequestSkillTarget(RequestSkillTargetEvent event) {
        // $ Select Skill Target
        for (int i = 0; i < 6; i++) {
            if (arrCharPane[i].getChildren().size() == 1) {
                arrCharPane[i].setStyle(
                        "-fx-border-radius: 0.5em;" +
                        "-fx-border-width: 0.5em;"+
                        "-fx-border-color: green"
                );
                int ID = i;
                arrCharPane[i].setOnMouseClicked(e -> {
                    publish("SUMMON_SKILL", new SummonSkillEvent(event.skill, event.id, ID, event.owner));
                    publish("SPEND_POWER_EVENT", new SpendPowerEvent(event.owner, event.skill.getElement(), event.skill.getPowVal()));
                    publish("ATTACH_SKILL", new SkillCardAttachedEvent(event.skill, board.getCharwithId(ID)));
                });
            }
        }
    }

    @Override
    public void onSelectEnemy(SelectEnemyEvent event) {
        if (!(event.SC.getOwner().equals(this.channel.activePlayer.getName()))) {
            boolean avail = false;
            for (int i = 0; i < 6; i++) {
                if (arrCharPane[i].getChildren().size() == 1) avail = true;
                break;
            }
            if (avail) {
                for (int i = 0; i < 6; i++) {
                    if (arrCharPane[i].getChildren().size() == 1) {
                        arrCharPane[i].setStyle(
                                "-fx-border-radius: 0.5em;" +
                                        "-fx-border-width: 0.5em;" +
                                        "-fx-border-color: #ff0000"
                        );
                        int ID = i;
                        arrSkillPane[i].setOnMouseClicked(e -> {
                            ResetStyle();
                            publish("ATTACK_CHARACTER_EVENT", new AttackCharacterEvent(event.SC, getBoard().getCharwithId(ID)));
                            this.channel.isSelecting = false;
                        });
                    }
                }
            } else {
                AlertPlayer noTarget = new AlertPlayer("There are no enemies to target!", Alert.AlertType.INFORMATION, "No Target");
                noTarget.show();
                this.channel.isSelecting = false;
                publish("ATTACK_FAIL", new AttackFailEvent(event.SC.getOwner()));
            }
        }
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof RequestSummonEvent) {
            this.onRequestSummon((RequestSummonEvent) event);
        } else if (event instanceof SummonCharacterEvent) {
            this.onSummonCharacterEvent((SummonCharacterEvent) event);
        } else if (event instanceof SummonSkillEvent) {
            this.onSummonSkillEvent((SummonSkillEvent) event);
        } else if (event instanceof RequestSkillTargetEvent) {
            this.onRequestSkillTarget((RequestSkillTargetEvent) event);
        } else if (event instanceof SelectEnemyEvent) {
            this.onSelectEnemy((SelectEnemyEvent) event);
        }
    }


}
