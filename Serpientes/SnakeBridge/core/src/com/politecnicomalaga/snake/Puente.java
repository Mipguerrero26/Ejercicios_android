package com.politecnicomalaga.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Puente {

    int pos1X, pos1Y, pos2X, pos2Y;
    static Cuadrado puente1;
    static Cuadrado puente2;

    public Puente() {
        pos1X = 20 * ( 8 +(1 + (int) Math.floor(Math.random()* ((Gdx.graphics.getWidth() / 20) - 17))));
        pos1Y = 20 * ( 8 +(1 + (int) Math.floor(Math.random()* ((Gdx.graphics.getHeight() / 20) - 17))));

        puente1 = new Cuadrado(pos1X,pos1Y,20);
        pos2X = 20 * ( 8 +(1 + (int) Math.floor(Math.random()* ((Gdx.graphics.getWidth() / 20) - 17))));
        pos2Y = 20 * ( 8 +(1 + (int) Math.floor(Math.random()* ((Gdx.graphics.getHeight() / 20) - 17))));

        puente2 = new Cuadrado(pos2X,pos2Y,20);
    }

    public boolean colisiona(float cabezaX, float cabezaY) {
        if (pos1X == cabezaX && pos1Y == cabezaY){
            return true;
        }

        if (pos2X == cabezaX && pos2Y == cabezaY){
            return true;
        }
        return false;
    }

    public void pintarse(SpriteBatch sb) {
        puente1.pintarseEsp(sb);
        puente2.pintarseEsp(sb);

    }

    public float getPuente1X() {
        return pos1X;
    }

    public float getPuente1Y() {
        return pos1Y;
    }

    public float getPuente2X() {
        return pos2X;
    }

    public float getPuente2Y() {
        return pos2Y;
    }

    public void dispose() {
        puente1.dispose();
        puente2.dispose();

    }

}
