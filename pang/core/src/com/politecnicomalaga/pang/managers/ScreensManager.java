package com.politecnicomalaga.pang.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.politecnicomalaga.pang.view.GameScreen;
import com.politecnicomalaga.pang.view.SplashScreen;

public class ScreensManager {

    // VARAIBLES
    public enum Screens {SPLASH, GAME};
    private static ScreensManager singleton;
    Screen activeScreen;

    private ScreensManager () {
    }

    //SINGLETON
    public static ScreensManager getSingleton(){
        if (singleton == null) {
            singleton = new ScreensManager();
        }
        return singleton;
    }

    public Screen getScreen(Game game, Screens screen) {
        activeScreen = null;

        ScreenUtils.clear(0, 0, 0, 1);
        switch (screen) {
            case SPLASH: activeScreen = new SplashScreen(game);
                break;
            case GAME: activeScreen = new GameScreen(game);
                break;
            default: screen = null;
                break;
        }

        return activeScreen;
    }

    public Object getScreen() {
        return activeScreen;
    }
}
