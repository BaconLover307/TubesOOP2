package com.avatarduel.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;

public class AlertChoice {

    private String text;
    private String title;
    private String choice1;
    private String choice2;
    private Alert alert;

    public AlertChoice(String choice1, String choice2, String text, String title) {
        this.text = text;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.title = title;
    }

    public String showAndReturn() {

        alert = new Alert(AlertType.INFORMATION);

        alert.setTitle(this.title);
        alert.setHeaderText(null);
        alert.setContentText(this.text);

        ButtonType button1 = new ButtonType(choice1);
        ButtonType button2 = new ButtonType(choice2);
        ButtonType cancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(button1, button2, cancel);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == button1) {
            alert.close();
            return choice1;
        } else if (result.get() == button2) {
            alert.close();
            return choice2;
        } else {
            alert.close();
            return "";
        }
    }

}