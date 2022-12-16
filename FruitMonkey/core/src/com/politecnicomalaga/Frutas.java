package com.politecnicomalaga;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Frutas {

    private static final String IMAGEN1 = "banana.png";
    private static final String IMAGEN2 = "ciruela.png";
    private static final String IMAGEN3 = "manzana.png";
    private static final String IMAGEN4 = "naranja.png";

    private Texture fruta;
    private float posX, posY;
    private int lado, tipo;

    public Frutas(float X, float Y, int l) {
        tipo = 1 + (int) (Math.random()* 4);
        posX = X;
        posY = Y;
        lado = l;

        if(tipo == 1){
            fruta = new Texture(IMAGEN1);
        }
        else if(tipo == 2){
            fruta = new Texture(IMAGEN2);
        }
        else if(tipo == 3){
            fruta = new Texture(IMAGEN3);
        }
        else if(tipo == 4){
            fruta = new Texture(IMAGEN4);
        }
    }

    public Frutas(Frutas otra) {
        tipo = 1 + (int) (Math.random()* 4);
        posX = otra.getPosX();
        posY = otra.getPosY();
        lado = otra.getLado();

        if(tipo == 1){
            fruta = new Texture(IMAGEN1);
        }
        else if(tipo == 2){
            fruta = new Texture(IMAGEN2);
        }
        else if(tipo == 3){
            fruta = new Texture(IMAGEN3);
        }
        else if(tipo == 4){
            fruta = new Texture(IMAGEN4);
        }
    }

    public void draw(SpriteBatch sb){
        sb.begin();
        sb.draw(fruta, posX, posY, lado, lado);
        sb.end();
    }

    //Colisiones
    public boolean colisiona(Mono mono) {
        //Columnas
        boolean resultado,colisionX,colisionY;

        colisionX = (Math.abs(posX - mono.getPosX()) <= (lado));
        colisionY = (Math.abs(posY - mono.getPosY()) <= (lado));
        resultado = colisionX && colisionY;

        return resultado;
    }

    public void moverse() {
        posY -= 4;

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
        fruta.dispose();
    }
}
