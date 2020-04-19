package com.avatarduel.view;

import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.Subscriber;
import com.avatarduel.model.gameplay.events.ResetPowerEvent;
import com.avatarduel.model.gameplay.events.SpendPowerEvent;
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
        UseLandEvent.UseLandEventHandler,
        ResetPowerEvent.ResetPowerEventHandler,
        SpendPowerEvent.SpendPowerEventHandler {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private VBox box;
    private Power power;
    private Label airLabel, earthLabel, fireLabel, waterLabel, energyLabel;
    private StringProperty air, earth, fire, water, energy;
    private double width, height;

    private VBox powerBox;
    private GameplayChannel channel;

    public PowerDisplay(GameplayChannel gameplayChannel, Power P) {
        this.channel = gameplayChannel;
        this.channel.addSubscriber("USE_LAND", this);
        this.channel.addSubscriber("SPEND_POWER_EVENT", this);
        this.channel.addSubscriber("RESET_POWER_EVENT", this);

        this.power = P;
        this.box = new VBox(16*screenSize.getHeight()/1080);
        this.width = (80*screenSize.getWidth()/1920);
        this.height = (260*screenSize.getHeight()/1080);
        this.box.setPrefSize(width,height);
        this.box.setAlignment(Pos.TOP_RIGHT);
        this.box.getStylesheets().add("com/avatarduel/css/powerStyle.css");

        this.air = new SimpleStringProperty(power.getPower(Element.AIR).getSize() + "/" + power.getPower(Element.AIR).getCapacity());
        this.airLabel = new Label();
        this.airLabel.textProperty().bind(this.air);
        this.airLabel.setPrefSize(80*screenSize.getWidth()/1920,40*screenSize.getHeight()/1080);
        this.airLabel.setAlignment(Pos.CENTER_RIGHT);
 
        this.fire = new SimpleStringProperty(power.getPower(Element.FIRE).getSize() + "/" + power.getPower(Element.FIRE).getCapacity());
        this.fireLabel = new Label();
        this.fireLabel.textProperty().bind(this.fire);
        this.fireLabel.setPrefSize(80*screenSize.getWidth()/1920,40*screenSize.getHeight()/1080);
        this.fireLabel.setAlignment(Pos.CENTER_RIGHT);

        this.earth = new SimpleStringProperty(power.getPower(Element.EARTH).getSize() + "/" + power.getPower(Element.EARTH).getCapacity());
        this.earthLabel = new Label();
        this.earthLabel.textProperty().bind(this.earth);
        this.earthLabel.setPrefSize(80*screenSize.getWidth()/1920,40*screenSize.getHeight()/1080);
        this.earthLabel.setAlignment(Pos.CENTER_RIGHT);

        this.water = new SimpleStringProperty(power.getPower(Element.WATER).getSize() + "/" + power.getPower(Element.WATER).getCapacity());
        this.waterLabel = new Label();
        this.waterLabel.textProperty().bind(this.water);
        this.waterLabel.setPrefSize(80*screenSize.getWidth()/1920,40*screenSize.getHeight()/1080);
        this.waterLabel.setAlignment(Pos.CENTER_RIGHT);

        this.energy = new SimpleStringProperty(power.getPower(Element.ENERGY).getSize() + "/" + power.getPower(Element.ENERGY).getCapacity());
        this.energyLabel = new Label();
        this.energyLabel.textProperty().bind(this.energy);
        this.energyLabel.setPrefSize(80*screenSize.getWidth()/1920,40*screenSize.getHeight()/1080);
        this.energyLabel.setAlignment(Pos.CENTER_RIGHT);

        box.getChildren().addAll(waterLabel, earthLabel, fireLabel, airLabel, energyLabel);
    }

    public Pane getPane() {return this.box;}

    public void updatePower() {
        this.water.setValue(power.getPower(Element.WATER).getSize() + "/" + power.getPower(Element.WATER).getCapacity());
        this.earth.setValue(power.getPower(Element.EARTH).getSize() + "/" + power.getPower(Element.EARTH).getCapacity());
        this.fire.setValue(power.getPower(Element.FIRE).getSize() + "/" + power.getPower(Element.FIRE).getCapacity());
        this.air.setValue(power.getPower(Element.AIR).getSize() + "/" + power.getPower(Element.AIR).getCapacity());
        this.energy.setValue(power.getPower(Element.ENERGY).getSize() + "/" + power.getPower(Element.ENERGY).getCapacity());
    }

    @Override
    public void onUseLandEvent(UseLandEvent e) {
        if (power.getOwner() == e.owner) {
            updatePower();
        }
    }

    @Override
    public void onResetPowerEvent(ResetPowerEvent e) {
        updatePower();
    }

    @Override
    public void onSpendPowerEvent(SpendPowerEvent e) {
        updatePower();
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof UseLandEvent) {
            onUseLandEvent((UseLandEvent) event);
        } else if (event instanceof SpendPowerEvent) {
            onSpendPowerEvent((SpendPowerEvent) event);
        } else if (event instanceof ResetPowerEvent) {
            onResetPowerEvent((ResetPowerEvent) event);
        }

    }
}