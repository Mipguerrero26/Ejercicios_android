package com.politecnicomalaga.pang.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.politecnicomalaga.pang.managers.AssetsManager;
import com.politecnicomalaga.pang.managers.GameManager;
import com.politecnicomalaga.pang.managers.SettingsManager;


public class Disparos extends Actor {

    private Animation<TextureRegion> skin;
    private Jugador player;

    private float iniX;

    private Rectangle body;


    public Disparos(Jugador player) {
        super();
        this.player = player;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_PATH));
        skin = new Animation<TextureRegion>(SettingsManager.PLAYER_ANIMATION_TIME, atlas.findRegions(AssetsManager.REGION_PLAYER_SHOT));

        this.setBounds(0, 0, SettingsManager.PLAYER_BULLET_WIDTH,  SettingsManager.PLAYER_BULLET_HEIGHT);

        iniX = player.getWidth()/2 - getWidth()/2;
        this.setX(player.getX() + iniX);
        this.setY(player.getY());
    }

    public void resetPos() {
        setX(player.getX() + iniX);
        setY(player.getY());
    }

    public Rectangle getBody() {
        return body = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, this.getX(), this.getY(), SettingsManager.PLAYER_BULLET_WIDTH,  SettingsManager.PLAYER_BULLET_HEIGHT);
    }
}
