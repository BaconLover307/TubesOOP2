package com.avatarduel.view;

import com.avatarduel.model.player.ElementPower;
import com.avatarduel.model.player.Power;
import com.avatarduel.model.Element;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class PowerDisplay {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Pane box,airbox,earthbox,firebox,waterbox;
    private Power P;
    private String air,earth,fire,water;
    private Text airText,earthText,fireText,waterText;
    private double width,height,posX,posY;

    public PowerDisplay(Power P, Pane pane) {

        this.P = P;
        box = new Pane();
        this.width = (400*screenSize.getWidth()/1920);
        this.height = (560*screenSize.getHeight()/1080);
        this.posY = (260*screenSize.getHeight()/1080);
        this.posX = (300*screenSize.getWidth()/1920);
        box.setPrefSize(width,height);
        box.relocate(posX,posY);

        air = Integer.toString(P.getPower(Element.AIR).getSize()) + "/" + Integer.toString(P.getPower(Element.AIR).getCapacity());
        earth = Integer.toString(P.getPower(Element.EARTH).getSize()) + "/" + Integer.toString(P.getPower(Element.EARTH).getCapacity());
        fire = Integer.toString(P.getPower(Element.FIRE).getSize()) + "/" + Integer.toString(P.getPower(Element.FIRE).getCapacity());
        water = Integer.toString(P.getPower(Element.WATER).getSize()) + "/" + Integer.toString(P.getPower(Element.WATER).getCapacity());

        airText = new Text();
        airText.setText(air);
        airbox = new Pane();
        airbox.getChildren().add(airText);

        earthText = new Text();
        earthText.setText(earth);
        earthbox = new Pane();
        earthbox.getChildren().add(earthText);

        fireText = new Text();
        fireText.setText(fire);
        firebox = new Pane();
        firebox.getChildren().add(fireText);

        waterText = new Text();
        waterText.setText(water);
        double Size = 0.034 * height;
        waterText.setFont(Font.font(java.awt.Font.SERIF, Size));
        waterbox = new Pane();
        waterbox.getChildren().add(waterText);
   

        pane.getChildren().addAll(waterbox);

    }
}