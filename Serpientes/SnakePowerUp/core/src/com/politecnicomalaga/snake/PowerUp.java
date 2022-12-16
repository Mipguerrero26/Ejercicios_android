package com.politecnicomalaga.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PowerUp {

    int posX, posY;
    Cuadrado entidad;

    public PowerUp() {
        posX = 20 * ( 1 +(1 + (int) Math.floor(Math.random()* ((Gdx.graphics.getWidth() / 20) - 3))));
        posY = 20 * ( 1 +(1 + (int) Math.floor(Math.random()* ((Gdx.graphics.getHeight() / 20) - 3))));

        entidad = new Cuadrado(posX,posY,20);
    }

    public void reubicacion(){

        dispose();

        posX = 20 * ( 1 +(1 + (int) Math.floor(Math.random()* ((Gdx.graphics.getWidth() / 20) - 3))));
        posY = 20 * ( 1 +(1 + (int) Math.floor(Math.random()* ((Gdx.graphics.getHeight() / 20) - 3))));

        entidad = new Cuadrado(posX,posY,20);
    }

    public boolean colisiona(float cabezaX, float cabezaY) {
        if (posX == cabezaX && posY == cabezaY){
            return true;
        }
        return false;
    }

    public void pintarse(SpriteBatch sb) {
        entidad.pintarseEsp(sb);

    }

    public void setPowerUpX(int powerUpX){
        posX = powerUpX;

    }

    public void setPowerUpY(int powerUpY){
        posY = powerUpY;

    }

    public void dispose() {
        entidad.dispose();

    }

}
