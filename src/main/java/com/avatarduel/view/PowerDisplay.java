package com.avatarduel.view;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.UseLandEvent;
import com.avatarduel.model.player.ElementPower;
import com.avatarduel.model.player.Power;
import com.avatarduel.model.Element;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.Node;

public class PowerDisplay implements Subscriber,
        UseLandEvent.UseLandEventHandler {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private VBox box;
    private Power power;
    private Label airLabel, earthLabel, fireLabel, waterLabel, energyLabel;
    private StringProperty air, earth, fire, water, energy;
    private StringProperty airSize, earthSize, fireSize, waterSize, energySize;
    private StringProperty airCap, earthCap, fireCap, waterCap, energyCap;
    private double width, height;

    private VBox powerBox;
    private GameplayChannel channel;

    public PowerDisplay(GameplayChannel gameplayChannel, Power P) {
        this.channel = gameplayChannel;
        this.channel.addSubscriber("USE_LAND", this);

        this.power = P;
        this.box = new VBox(16*screenSize.getHeight()/1080);
        this.width = (80*screenSize.getWidth()/1920);
        this.height = (260*screenSize.getHeight()/1080);
        this.box.setPrefSize(width,height);
        this.box.setAlignment(Pos.TOP_RIGHT);
        this.box.getStylesheets().add("com/avatarduel/css/powerStyle.css");

        this.airSize = new SimpleStringProperty(Integer.toString(power.getPower(Element.AIR).getSize()));
        this.airCap = new SimpleStringProperty(Integer.toString(power.getPower(Element.AIR).getCapacity()));
        this.air = new SimpleStringProperty(this.airSize.getValue() + "/" + this.airCap.getValue());
//        this.air.setValue(this.airSize + "/" + this.airCap);
        this.airLabel = new Label();
        this.airLabel.textProperty().bind(this.air);
        this.airLabel.setPrefSize(80*screenSize.getWidth()/1920,40*screenSize.getHeight()/1080);
        this.airLabel.setAlignment(Pos.CENTER_RIGHT);
 
        this.fireSize = new SimpleStringProperty(Integer.toString(power.getPower(Element.FIRE).getSize()));
        this.fireCap = new SimpleStringProperty(Integer.toString(power.getPower(Element.FIRE).getCapacity()));
        this.fire = new SimpleStringProperty(this.fireSize.getValue() + "/" + this.fireCap.getValue());
        this.fireLabel = new Label();
        this.fireLabel.textProperty().bind(this.fire);
        this.fireLabel.setPrefSize(80*screenSize.getWidth()/1920,40*screenSize.getHeight()/1080);
        this.fireLabel.setAlignment(Pos.CENTER_RIGHT);

        this.earthSize = new SimpleStringProperty(Integer.toString(power.getPower(Element.EARTH).getSize()));
        this.earthCap = new SimpleStringProperty(Integer.toString(power.getPower(Element.EARTH).getCapacity()));
        this.earth = new SimpleStringProperty(this.earthSize.getValue() + "/" + this.earthCap.getValue());
        this.earthLabel = new Label();
        this.earthLabel.textProperty().bind(this.earth);
        this.earthLabel.setPrefSize(80*screenSize.getWidth()/1920,40*screenSize.getHeight()/1080);
        this.earthLabel.setAlignment(Pos.CENTER_RIGHT);

        this.waterSize = new SimpleStringProperty(Integer.toString(power.getPower(Element.WATER).getSize()));
        this.waterCap = new SimpleStringProperty(Integer.toString(power.getPower(Element.WATER).getCapacity()));
        this.water = new SimpleStringProperty(this.waterSize.getValue() + "/" + this.waterCap.getValue());
        this.waterLabel = new Label();
        this.waterLabel.textProperty().bind(this.water);
        this.waterLabel.setPrefSize(80*screenSize.getWidth()/1920,40*screenSize.getHeight()/1080);
        this.waterLabel.setAlignment(Pos.CENTER_RIGHT);

        this.energySize = new SimpleStringProperty(Integer.toString(power.getPower(Element.ENERGY).getSize()));
        this.energyCap = new SimpleStringProperty(Integer.toString(power.getPower(Element.ENERGY).getCapacity()));
        this.energy = new SimpleStringProperty(this.energySize.getValue() + "/" + this.energyCap.getValue());
        this.energyLabel = new Label();
        this.energyLabel.textProperty().bind(this.energy);
        this.energyLabel.setPrefSize(80*screenSize.getWidth()/1920,40*screenSize.getHeight()/1080);
        this.energyLabel.setAlignment(Pos.CENTER_RIGHT);

        box.getChildren().addAll(waterLabel, earthLabel, fireLabel, airLabel, energyLabel);
    }

    public Pane getPane() {return this.box;}

    public void updatePower() {
        this.waterCap.setValue(Integer.toString(power.getPower(Element.WATER).getCapacity()));
        this.waterSize.setValue(Integer.toString(power.getPower(Element.WATER).getSize()));
        this.earthCap.setValue(Integer.toString(power.getPower(Element.EARTH).getCapacity()));
        this.earthSize.setValue(Integer.toString(power.getPower(Element.EARTH).getSize()));
        this.fireCap.setValue(Integer.toString(power.getPower(Element.FIRE).getCapacity()));
        this.fireSize.setValue(Integer.toString(power.getPower(Element.FIRE).getSize()));
        this.airCap.setValue(Integer.toString(power.getPower(Element.AIR).getCapacity()));
        this.airSize.setValue(Integer.toString(power.getPower(Element.AIR).getSize()));
        this.energyCap.setValue(Integer.toString(power.getPower(Element.ENERGY).getCapacity()));
        this.energySize.setValue(Integer.toString(power.getPower(Element.ENERGY).getSize()));
    }

    @Override
    public void onUseLandEvent(UseLandEvent e) {
        if (power.getOwner() == e.owner) {
            updatePower();
        }
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof UseLandEvent) {
            onUseLandEvent((UseLandEvent) event);
        }

    }
}