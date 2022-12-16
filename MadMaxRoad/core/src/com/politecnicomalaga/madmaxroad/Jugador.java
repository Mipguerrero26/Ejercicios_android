package com.politecnicomalaga.madmaxroad;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Jugador {

    public enum Direccion {ARRIBA, ABAJO, QUIETO};

    private static final String IMAGEN = "interceptor.png";

    private Texture jugador;
    private float posX, posY;
    private int lado;

    public Jugador(float X, float Y, int l) {
        posX = X;
        posY = Y;
        lado = l;

        jugador = new Texture(IMAGEN);
    }

    public void draw(SpriteBatch sb){
        sb.begin();
        sb.draw(jugador, posX, posY, lado, lado);
        sb.end();
    }

    public void moverse(Direccion dir) {
        switch (dir) {
            case ARRIBA:posY += 2;
                break;
            case QUIETO:posY += 0;
                break;
            case ABAJO:posY -= 2;
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
        jugador.dispose();
    }
}
