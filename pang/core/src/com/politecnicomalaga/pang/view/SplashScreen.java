package com.politecnicomalaga.pang.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.politecnicomalaga.pang.managers.AssetsManager;
import com.politecnicomalaga.pang.managers.ScreensManager;

public class SplashScreen implements Screen {

    private Stage stage;
    private Game game;
    private  SpriteBatch batch;
    private Skin skin;

    Texture imgInicio;
    Music introMusic;

    public SplashScreen(final Game aGame){
        game = aGame;

        introMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.INTRO_MUSIC));
        introMusic.setLooping(true);
        introMusic.play();

        batch = new SpriteBatch();
        imgInicio = new Texture(AssetsManager.SPLASH_SCREEN);

        stage = new Stage(new ScreenViewport());

        skin = AssetsManager.getSingleton().getTextSkin();

        Gdx.input.setInputProcessor(stage);

        TextButton playButton = new TextButton("START", skin, "small");
        playButton.setSize((Gdx.graphics.getWidth()/8) * 2, Gdx.graphics.getHeight()/8);
        playButton.setPosition(stage.getWidth()/2 - playButton.getWidth()/2.5f,stage.getHeight()/8 + playButton.getHeight()/2.5f);

        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                introMusic.stop();
                game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.Screens.GAME));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(playButton);
        playButton.setTransform(true);
        playButton.setScale(0.75f);
    }


    @Override
    public void show() {
        Gdx.app.log("SplashScreen","show");
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(imgInicio,0,0);
        batch.end();

        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose() {
        imgInicio.dispose();
        stage.dispose();
    }
}
