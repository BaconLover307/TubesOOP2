package com.avatarduel.view;

import java.awt.*;

public interface BaseView {
    public static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final double SCREENW = SCREENSIZE.getWidth();
    public static final double SCREENH = SCREENSIZE.getHeight();
    public static final double CARD_SIZEW = SCREENW * 80 / 1920;
    public static final double CARD_SIZEH = SCREENH * 112 / 1080;
    public static final double CARD_DISPLAY_SIZEW = SCREENW * 400 / 1920;
    public static final double CARD_DISPLAY_SIZEH = SCREENH * 560 / 1080;
}
