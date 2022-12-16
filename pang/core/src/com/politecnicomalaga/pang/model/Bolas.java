package com.politecnicomalaga.pang.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.politecnicomalaga.pang.managers.AssetsManager;
import com.politecnicomalaga.pang.managers.ScreensManager;
import com.politecnicomalaga.pang.managers.SettingsManager;
import com.politecnicomalaga.pang.view.GameScreen;

public class Bolas extends Actor {
    //Atributos
    private Texture skin;
    private Circle body;

    int tipo;
    float velX, velY;
    float size, radio, posX, posY;

    //Constructor
    public Bolas(float posX, float posY,float radio, Stage miContenedor) {
        super();
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_PATH));

        tipo = 1 + (int) (Math.random()* 3);

        if(tipo == 1){
            skin = new Texture(AssetsManager.CIRCULO_AZUL);
        }
        else if(tipo == 2){
            skin = new Texture(AssetsManager.CIRCULO_ROJO);
        }
        else if(tipo == 3){
            skin = new Texture(AssetsManager.CIRCULO_VERDE);
        }

        this.radio = radio;
        size = radio * 2;
        this.posX = posX;
        this.posY = posY;

        this.setBounds(0, 0, size,  size);
        this.setX(posX);
        this.setY(posY);

        updateBody();

        velX = SettingsManager.VEL_X;
        velY = -SettingsManager.VEL_Y;

    }

    public void moverse() {
        if (this.getX() <= 0 || this.getX() + (radio * 2) >= Gdx.graphics.getWidth()){
            velX = -velX;
        }

        this.setX((float) (this.getX() + velX));
        this.setY((float) (this.getY() + velY));

        velY = (float) (velY - SettingsManager.ACEL_Y);

        if (this.getY() <= 20 || this.getY() + (radio * 2) >= Gdx.graphics.getHeight()){
            velY = 0.115f * radio + 6;
        }

    }

    public void dividir (Stage stage){
        GameScreen gs = (GameScreen) ScreensManager.getSingleton().getScreen();
        float newRadio= getRadio()/2;

        if (radio > 15f) {
            float posX1 = getX() - radio;
            float posY1 = getY();
            Bolas newBola1 = new Bolas(posX1, posY1, newRadio, stage);
            newBola1.setVelX(velX);
            stage.addActor(newBola1);
            gs.getBolas().add(newBola1);

            float posX2 = getX() + radio;
            float posY2 = getY();
            Bolas newBola2 = new Bolas(posX2, posY2, newRadio, stage);
            newBola2.setVelX(-velX);
            stage.addActor(newBola2);
            gs.getBolas().add(newBola2);
        }
        remove();
    }

    private void updateBody() {
        body = new Circle(getX() + radio, getY() + radio, radio);
    }

    public Circle getBody() {
        return new Circle(getX() + radio, getY() + radio, radio);
    }


    public float getRadio(){
        return radio;
    }

    public void setVelX (Float velX){
        this.velX = velX;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(skin, this.getX(), this.getY(), size ,  size);
    }

}
