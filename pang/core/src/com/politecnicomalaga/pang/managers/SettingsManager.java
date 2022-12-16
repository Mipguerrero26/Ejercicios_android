package com.politecnicomalaga.pang.managers;

public class SettingsManager {
    // WINDOW
    public static final short SCREEN_WIDTH = 1024;
    public static final short SCREEN_HEIGHT = 768;
    public static final short SCREEN_MID_WIDTH = 1024 / 2;

    // ACTOR
    public static final short PLAYER_SIZE = 64;
    public static final short PLAYER_HOR_POS = SCREEN_MID_WIDTH;
    public static final short PLAYER_VER_POS = 20;
    public static final short PLAYER_VEl= 3;
    public static final String SENTIDO = "DERECHA";

    public static final short BOLA_RADIO = 75;
    public static final short BOLA_HOR_POS = (SCREEN_WIDTH / 2 ) - BOLA_RADIO;
    public static final short BOLA_VER_POS = (SCREEN_HEIGHT / 3 ) * 2;
    public static final double ACEL_Y = 0.2;
    public static float VEL_X = 3;
    public static float VEL_Y = 0;

    //SHOT
    public static final short PLAYER_BULLET_WIDTH = 32;
    public static final short PLAYER_BULLET_HEIGHT = 16;
    public static final byte BULLET_PLAYER_SPEED = 10;
    public static final float BULLET_PLAYER_RATIO = 1f;

    // ANIMATION
    public static final float PLAYER_ANIMATION_TIME = 0.25f;
}
