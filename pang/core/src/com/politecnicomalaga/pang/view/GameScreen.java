package com.politecnicomalaga.pang.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.Array;
import com.politecnicomalaga.pang.managers.AssetsManager;
import com.politecnicomalaga.pang.managers.SettingsManager;
import com.politecnicomalaga.pang.model.Bolas;
import com.politecnicomalaga.pang.model.Disparos;
import com.politecnicomalaga.pang.model.Jugador;

public class GameScreen  implements Screen {

    private Stage stage;
    private Game game;
    private  SpriteBatch batch;

    private Texture imgJugando;
    private Music playMusic, morirMusic, ganarMusic, explotarMusic;
    private Jugador player;
    private Array<Bolas> bolas;
    private Bolas bola;

    int tipo, direccion;
    boolean endGame;
    float radio;

    public GameScreen(final Game aGame){
        game = aGame;

        tipo = 1 + (int) (Math.random()* 3);

        if(tipo == 1){
            playMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.GAME_MUSIC1));
        }
        else if(tipo == 2){
            playMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.GAME_MUSIC2));
        }
        else if(tipo == 3){
            playMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.GAME_MUSIC3));
        }

        playMusic.setLooping(true);
        playMusic.play();

        morirMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.MORIR_MUSIC));
        morirMusic.setLooping(false);

        ganarMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.GANAR_MUSIC));
        ganarMusic.setLooping(false);

        explotarMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.EXPLOTAR_MUSIC));
        explotarMusic.setLooping(false);

        batch = new SpriteBatch();
        imgJugando = new Texture(AssetsManager.GAME_SCREEN);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        player = new Jugador();
        stage.addActor(player);
        player.canShoot(true);

        radio = SettingsManager.BOLA_RADIO;

        bolas = new Array<>();

        bola = new Bolas(SettingsManager.BOLA_HOR_POS, SettingsManager.BOLA_VER_POS, radio, stage);
        stage.addActor(bola);

        bolas.add(bola);

        endGame = false;

    }

    public void checkCollisionsBolas() {
        Array<Disparos> activeBullets = player.getActiveBullets();
        for (int f=0; f<activeBullets.size; f++) {
            Disparos bullet = activeBullets.get(f);
            for (Bolas todasBolas: bolas) {
                if (Intersector.overlaps(todasBolas.getBody(), bullet.getBody())) {

                    bullet.setY(1589);
                    player.getActiveBullets().removeValue(bullet, true);
                    bullet.remove();

                    explotarMusic.play();

                    stage.getActors().removeValue(todasBolas, true);
                    bolas.removeValue(todasBolas, true);

                    todasBolas.dividir(stage);
                    stage.addActor(todasBolas);

                }
            }

        }

        if (bolas.size == 0){
            playMusic.stop();
            ganarMusic.play();
            player.canShoot(false);
            endGame = true;
        }
    }

    public void checkCollisionsJugador() {
        for (Bolas todasBolas: bolas) {
            if (Intersector.overlaps(todasBolas.getBody(), player.getBody())) {
                playMusic.stop();
                morirMusic.play();
                player.canShoot(false);
                endGame = true;

            }
        }
    }

    public Array<Bolas> getBolas(){
        return bolas;
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen","show");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!endGame) {

            //COLLISIONS
            checkCollisionsBolas();
            checkCollisionsJugador();

            if (Gdx.input.justTouched()) {

                if (Gdx.input.getX() > 2 * (Gdx.graphics.getWidth() / 3)) {
                    direccion = 1;
                } else if (Gdx.input.getX() < Gdx.graphics.getWidth() / 3) {
                    direccion = 3;
                } else {
                    direccion = 2;
                }

            }

            if (direccion == 1 && player.getX() + SettingsManager.PLAYER_SIZE < Gdx.graphics.getWidth()) {
                player.moverse(Jugador.Direccion.DERECHA);
            } else if (direccion == 3 && player.getX() > 0) {
                player.moverse(Jugador.Direccion.IZQUIERDA);
            } else {
                player.moverse(Jugador.Direccion.QUIETO);
            }

            for (Bolas bolitas : bolas) {
                bolitas.moverse();
            }
        }

        batch.begin();
        batch.draw(imgJugando, 0, 0);

        batch.end();

        stage.act(delta);
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
        imgJugando.dispose();
        stage.dispose();
        playMusic.dispose();
        morirMusic.dispose();
        ganarMusic.dispose();
        explotarMusic.dispose();
    }
}
