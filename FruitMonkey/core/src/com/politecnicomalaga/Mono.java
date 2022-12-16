package com.politecnicomalaga;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mono {

    public enum Direccion {IZQUIERDA, DERECHA, QUIETO};

    private static final String IMAGEN = "gorilla.png";

    private Texture mono;
    private float posX, posY;
    private int lado;

    public Mono(float X, float Y, int l) {
        posX = X;
        posY = Y;
        lado = l;

        mono = new Texture(IMAGEN);
    }

    public void draw(SpriteBatch sb){
        sb.begin();
        sb.draw(mono, posX, posY, lado, lado);
        sb.end();
    }

    public void moverse(Direccion dir) {
        switch (dir) {
            case DERECHA:posX += 2;
                break;
            case QUIETO:posX += 0;
                break;
            case IZQUIERDA:posX -= 2;
                break;
        }
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public void dispose() {
        mono.dispose();
    }
}
