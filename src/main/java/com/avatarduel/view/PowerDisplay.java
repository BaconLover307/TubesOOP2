package com.avatarduel.view;

import com.avatarduel.model.player.ElementPower;
import com.avatarduel.model.player.Power;
import com.avatarduel.model.Element;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.Node;

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
        double Size = 0.034 * height;
        box.setPrefSize(width,height);
        box.relocate(posX,posY);

        air = Integer.toString(P.getPower(Element.AIR).getSize()) + "/" + Integer.toString(P.getPower(Element.AIR).getCapacity());
        earth = Integer.toString(P.getPower(Element.EARTH).getSize()) + "/" + Integer.toString(P.getPower(Element.EARTH).getCapacity());
        fire = Integer.toString(P.getPower(Element.FIRE).getSize()) + "/" + Integer.toString(P.getPower(Element.FIRE).getCapacity());
        water = Integer.toString(P.getPower(Element.WATER).getSize()) + "/" + Integer.toString(P.getPower(Element.WATER).getCapacity());
 
        airText = new Text();
        airText.setText(air);
        airText.setFont(Font.font(java.awt.Font.SERIF, Size));
        airText.setX(posX);
        airText.setY(posY-60);
        airbox = new Pane();
        airbox.getChildren().add(airText);

        earthText = new Text();
        earthText.setText(earth);
        earthText.setFont(Font.font(java.awt.Font.SERIF, Size));
        earthText.setX(posX);
        earthText.setY(posY-40);
        earthbox = new Pane();
        earthbox.getChildren().add(earthText);

        fireText = new Text();
        fireText.setText(fire);
        fireText.setFont(Font.font(java.awt.Font.SERIF, Size));
        fireText.setX(posX);
        fireText.setY(posY-20);
        firebox = new Pane();
        firebox.getChildren().add(fireText);

        waterText = new Text();
        waterText.setText(water);
        waterText.setFont(Font.font(java.awt.Font.SERIF, Size));
        waterText.setX(posX);
        waterText.setY(posY);
        waterbox = new Pane();
        waterbox.getChildren().add(waterText);

        box.getChildren().addAll(waterbox,earthbox,firebox,airbox);
        box.setVisible(true);
        pane.getChildren().add(box);

    }
}