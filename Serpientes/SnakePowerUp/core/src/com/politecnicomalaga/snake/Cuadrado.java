package com.politecnicomalaga.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cuadrado {

    public enum Direccion {ARRIBA, ABAJO, IZQUIERDA, DERECHA};

    private static final String IMAGEN = "cuadrado.png";
    private static final String IMAGEN2 = "cuadrado2.png";
    private static final String IMAGEN3 = "cuadrado3.png";
    private static final String CABEZA = "cabeza.png";

    //Atributos
    protected Texture colorRamd, colorEspecial;
    protected float posX,posY;
    protected int lado, tipo;

    //Métodos
    public Cuadrado(float X, float Y, int l) {
        tipo = 1 + (int) (Math.random()* 3);
        posX = X;
        posY = Y;
        lado = l;

        if(tipo == 1){
            colorRamd = new Texture(IMAGEN);
        }
        else if(tipo == 2){
            colorRamd = new Texture(IMAGEN2);
        }
        else if(tipo == 3){
            colorRamd = new Texture(IMAGEN3);
        }

        colorEspecial = new Texture(CABEZA);
    }

    public Cuadrado(Cuadrado otro) {
        tipo = otro.getTipo();
        posX = otro.getPosX();
        posY = otro.getPosY();
        lado = otro.getLado();

        if(tipo == 1){
            colorRamd = new Texture(IMAGEN);
        }
        else if(tipo == 2){
            colorRamd = new Texture(IMAGEN2);
        }
        else if(tipo == 3){
            colorRamd = new Texture(IMAGEN3);
        }

        colorEspecial = new Texture(CABEZA);

    }

    public void pintarseRamd(SpriteBatch sb) {
        sb.begin();
        sb.draw(colorRamd, posX, posY, lado, lado);
        sb.end();
    }

    public void pintarseEsp(SpriteBatch sb) {
        sb.begin();
        sb.draw(colorEspecial, posX, posY, lado, lado);
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

    public int getTipo() {
        return tipo;
    }

    public void dispose() {
        if (colorRamd != null) colorRamd.dispose();
        colorEspecial.dispose();
    }

}
