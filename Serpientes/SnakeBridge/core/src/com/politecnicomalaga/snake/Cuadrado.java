package com.politecnicomalaga.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cuadrado {

    public enum Direccion {ARRIBA, ABAJO, IZQUIERDA, DERECHA};

    private static final String IMAGEN = "cuadrado.png";
    private static final String CABEZA = "cabeza.png";

    //Atributos
    protected Texture color, colorCabeza;
    protected float posX,posY;
    protected int lado;

    //Métodos
    public Cuadrado(float X, float Y, int l) {
        posX = X;
        posY = Y;
        lado = l;

        color = new Texture(IMAGEN);
        colorCabeza = new Texture(CABEZA);
    }

    public Cuadrado(Cuadrado otro) {
        posX = otro.getPosX();
        posY = otro.getPosY();
        lado = otro.getLado();

        color = new Texture(IMAGEN);
        colorCabeza = new Texture(CABEZA);

    }

    public void pintarseRamd(SpriteBatch sb) {
        sb.begin();
        sb.draw(color, posX, posY, lado, lado);
        sb.end();
    }

    public void pintarseEsp(SpriteBatch sb) {
        sb.begin();
        sb.draw(colorCabeza, posX, posY, lado, lado);
        sb.end();
    }


    //Moverse
    public void moverse(Direccion dir) {
        switch (dir) {
            case ARRIBA:posY += lado;
                break;
            case ABAJO:posY -= lado;
                break;
            case DERECHA:posX += lado;
                break;
            case IZQUIERDA:posX -= lado;
                break;
        }
    }

    //Colisiona
    public boolean colisiona(Cuadrado otro) {
        return (otro.getPosX()==posX && otro.getPosY()==posY);
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public int getLado() {
        return lado;
    }


    public void dispose() {
        if (color != null) color.dispose();
        colorCabeza.dispose();
    }

}
