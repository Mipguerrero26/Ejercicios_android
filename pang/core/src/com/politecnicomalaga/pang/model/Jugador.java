package com.politecnicomalaga.pang.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.politecnicomalaga.pang.managers.AssetsManager;
import com.politecnicomalaga.pang.managers.GameManager;
import com.politecnicomalaga.pang.managers.SettingsManager;

import java.util.Stack;

public class Jugador extends Actor {

    private Animation<TextureRegion> skinDerch, skinIzq;

    private Array<Disparos> activeBullets;
    private Stack<Disparos> inactiveBullets;

    private Rectangle body;

    private float deltaBullet;

    private boolean isShooting;

    public enum Direccion {IZQUIERDA, DERECHA, QUIETO};
    private String sentido;

    public Jugador() {
        super();
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_PATH));
        skinDerch = new Animation<TextureRegion>(SettingsManager.PLAYER_ANIMATION_TIME, atlas.findRegions(AssetsManager.PLAYER_DERCH), Animation.PlayMode.LOOP);
        skinIzq = new Animation<TextureRegion>(SettingsManager.PLAYER_ANIMATION_TIME, atlas.findRegions(AssetsManager.PLAYER_IZQ), Animation.PlayMode.LOOP);
        this.setBounds(0, 0, SettingsManager.PLAYER_SIZE,  SettingsManager.PLAYER_SIZE);
        this.setX(SettingsManager.PLAYER_HOR_POS - SettingsManager.PLAYER_SIZE/2);
        this.setY(SettingsManager.PLAYER_VER_POS);

        sentido = SettingsManager.SENTIDO;

        updateBody();

        deltaBullet = GameManager.getSingleton().getGameTime();

        activeBullets = new Array<Disparos>();
        inactiveBullets = new Stack<Disparos>();
    }

    public void canShoot(boolean canShoot) {
        isShooting = canShoot;
    }

    public void shoot() {
        if (inactiveBullets.size() == 0) {
            activeBullets.add(new Disparos(this));
        } else {
            transferBullet();
        }
    }

    public Array<Disparos> getBullets() {
        return activeBullets;
    }

    private void transferBullet(int pos) {
        Disparos newBullet = activeBullets.get(pos);
        activeBullets.removeIndex(pos);
        inactiveBullets.push(newBullet);
    }

    private void transferBullet() {
        Disparos newBullet = inactiveBullets.pop();
        newBullet.resetPos();
        activeBullets.add(newBullet);
    }

    private void updateBody() {
        body = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getBody() {
        return body = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void moverse(Direccion dir) {
        switch (dir) {
            case DERECHA:this.setX(this.getX() + SettingsManager.PLAYER_VEl);
                sentido = "DERECHA";
                break;
            case QUIETO:this.setX(this.getX());
                break;
            case IZQUIERDA:this.setX(this.getX() - SettingsManager.PLAYER_VEl);
                sentido = "IZQUIERDA";
                break;
        }

    }
    public Array<Disparos> getActiveBullets() {
        return activeBullets;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isShooting) {
            if (deltaBullet+SettingsManager.BULLET_PLAYER_RATIO < GameManager.getSingleton().getGameTime()) {
                deltaBullet = GameManager.getSingleton().getGameTime();
                shoot();
            }
        }

        for (int f=0; f<activeBullets.size; f++) {
            if (activeBullets.get(f).getY() > SettingsManager.SCREEN_HEIGHT) {
                transferBullet(f);
            } else {
                break;
            }
        }

        for (int f=0; f<activeBullets.size; f++) {
            activeBullets.get(f).setY(activeBullets.get(f).getY()+SettingsManager.BULLET_PLAYER_SPEED);
            activeBullets.get(f).draw(batch, parentAlpha);
        }

        super.draw(batch, parentAlpha);

        if (sentido == "DERECHA"){
            TextureRegion currentFrame = skinDerch.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
            batch.draw(currentFrame, this.getX(), this.getY(), SettingsManager.PLAYER_SIZE,  SettingsManager.PLAYER_SIZE);
        }
        else {
            TextureRegion currentFrame = skinIzq.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
            batch.draw(currentFrame, this.getX(), this.getY(), SettingsManager.PLAYER_SIZE,  SettingsManager.PLAYER_SIZE);
        }

    }
}
