package com.politecnicomalaga.pang.managers;

import com.badlogic.gdx.Gdx;

public class GameManager {

    private float gameTime;
    private static GameManager singleton;

    private GameManager() {}

    public static GameManager getSingleton() {
        if (singleton == null) {
            singleton = new GameManager();
        }
        return singleton;
    }

    public float getGameTime() {
        return gameTime;
    }

    public float addDelta() {
        gameTime += Gdx.graphics.getDeltaTime();
        return gameTime;
    }
}
