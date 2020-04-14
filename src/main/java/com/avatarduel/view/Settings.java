package com.avatarduel.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Settings {

    private int amount;

    public Settings() {

    }

    public void showSettings(int n) {
        Stage settings = new Stage();
        settings.initModality(Modality.APPLICATION_MODAL);
        settings.setTitle("Settings");

        amount = n;
        String txt = "Cards on Deck: ";
        Label cardLabel = new Label(txt + amount);
        Slider cardSlider = new Slider(40, 60, amount);
        cardSlider.setShowTickLabels(true);
        cardSlider.setShowTickMarks(true);
        cardSlider.setSnapToTicks(true);
        cardSlider.setMajorTickUnit(5);
        cardSlider.setBlockIncrement(1);
        cardSlider.setMaxWidth(250);
        cardSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> cardLabel.setText(txt + newValue.intValue())
        );

        Button btnSet = new Button("Set");
        Button btnCancel = new Button("Cancel");
        btnSet.setOnAction(e -> {
            amount = cardSlider.valueProperty().getValue().intValue();
            settings.close();
        });
        btnCancel.setOnAction(e -> settings.close());

        HBox confirm = new HBox(15);
        confirm.setAlignment(Pos.CENTER);
        confirm.getChildren().addAll(btnSet,btnCancel);
        VBox settingsBox = new VBox(10);
        settingsBox.setAlignment(Pos.CENTER);
        settingsBox.getChildren().addAll(cardLabel,cardSlider, confirm);
        Scene s = new Scene(settingsBox, 300, 150);
        settings.setScene(s);
        settings.showAndWait();
    }

    public int getCardAmount() {return amount;}
}