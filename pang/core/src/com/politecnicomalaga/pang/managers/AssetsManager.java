package com.politecnicomalaga.pang.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsManager {

    // VARIABLES
    private static Skin skin;
    private static AssetsManager singleton;

    //MUSIC
    public static final String INTRO_MUSIC ="sounds/intro_pang.wav";
    public static final String GAME_MUSIC1 ="sounds/jugando1_pang.wav";
    public static final String GAME_MUSIC2 ="sounds/jugando2_pang.wav";
    public static final String GAME_MUSIC3 ="sounds/jugando3_pang.wav";
    public static final String MORIR_MUSIC ="sounds/morir_pang.wav";
    public static final String GANAR_MUSIC ="sounds/ganar_pang.wav";
    public static final String EXPLOTAR_MUSIC ="sounds/explotar.wav";

    //BACKGROUND
    public static final String GAME_SCREEN ="screens/fondo.jpg";
    public static final String SPLASH_SCREEN ="screens/pantalla_inicial.png";
    public static final String CIRCULO_ROJO ="sprites/circuloRojo.png";
    public static final String CIRCULO_AZUL ="sprites/circuloAzul.png";
    public static final String CIRCULO_VERDE ="sprites/circuloVerde.png";

    //ATLAS
    public static final String ATLAS_PATH ="sprites/pang.atlas";
    public static final String PLAYER_DERCH ="andando";
    public static final String PLAYER_IZQ ="andandoIZQ";
    public static final String REGION_PLAYER_SHOT ="disparo";

    // CONSTRUCTOR
    private AssetsManager() {}


    public static AssetsManager getSingleton() {
        if (singleton == null) {
            singleton = new AssetsManager();
        }
        return singleton;
    }

    public Skin getTextSkin() {
        if (skin == null) {
            skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        }
        return skin;
    }

}//CLASS
