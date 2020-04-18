package com.avatarduel.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;

public class AlertPlayer {

    private String text;
    private AlertType type;
    private String title;
    private Alert alert;

    public AlertPlayer(String text, AlertType type, String title) {
        this.text = text;
        this.type = type;
        this.title = title;
    }

    public void show() {

        if (this.type == AlertType.INFORMATION) {
            alert = new Alert(AlertType.INFORMATION);
        } else {
            alert = new Alert(AlertType.WARNING);
        }

        alert.setTitle(this.title);
        alert.setHeaderText(null);
        alert.setContentText(this.text);

        ButtonType buttonOK = new ButtonType("OK");
        alert.getButtonTypes().setAll(buttonOK);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonOK) {
            alert.close();
        }
    }

/*        Stage stage = new Stage();
        stage.setTitle("Info Turn");
        Label prompt = new Label(this.name + "'s Turn!");

        Button OKbutton = new Button("OK");
        OKbutton.setOnAction(e -> {
            stage.close();
        });

        VBox box = new VBox(10);

        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(prompt, OKbutton);

        Scene s = new Scene(box, 220, 105);
        stage.setScene(s);
        stage.showAndWait();

    } */
}