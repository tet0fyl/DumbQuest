package utils;

import javafx.stage.Screen;

public class Config {
    public static final double screenWidth = Screen.getPrimary().getBounds().getWidth();
    public static final double screenHeight = Screen.getPrimary().getBounds().getHeight();
    public static final double gameWindowWidth = screenWidth;
    public static final double gameWindowHeight = screenHeight+1;
}
