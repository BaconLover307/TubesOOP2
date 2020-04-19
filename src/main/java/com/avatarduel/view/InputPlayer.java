package com.avatarduel.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;

public class InputPlayer {
    private String name;
    private int player;
    private final int LIMIT = 13;

    public InputPlayer(int n) {
        this.player = n;
        this.name = "PLayer" + n;
    }

    public void showInputPlayer() {
        Stage stage = new Stage();
        stage.setTitle("Player " + player);

        Label prompt = new Label("Enter your name, Player" + player);
        TextField inputName = new TextField("Player" + player);
        inputName.setMaxWidth(120);
        inputName.lengthProperty().addListener( // Limits the player name to LIMIT
                (observable, oldValue, newValue) -> {
                        if (newValue.intValue() > oldValue.intValue()) {
                            if (inputName.getText().length() >= LIMIT) {
                                inputName.setText(inputName.getText().substring(0,LIMIT));
                            }
                        }
                });
        inputName.setOnKeyPressed( e -> {// Submits name on ENTER
                    if (e.getCode() == KeyCode.ENTER) {
                        name = inputName.getText();
                        stage.close();
                    }
                });
        Button btnConfirm = new Button("OK");
        btnConfirm.setOnAction(e -> {
            if (inputName.getText() != null && inputName.getText() != "") {
                name = inputName.getText();
            }
            stage.close();
        });

        VBox box = new VBox(10);

        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(prompt,inputName,btnConfirm);

        Scene s = new Scene(box, 220, 105);
        stage.setScene(s);
        stage.showAndWait();
//        Scene scene = new Scene(stage);
    }

    public String getName() {return name;}
}
