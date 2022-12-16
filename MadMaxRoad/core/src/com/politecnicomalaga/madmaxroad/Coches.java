package com.politecnicomalaga.madmaxroad;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Coches {

    private static final String IMAGEN1 = "warcar1.png";
    private static final String IMAGEN2 = "warcar2.png";
    private static final String IMAGEN3 = "warcar3.png";
    private static final String IMAGEN4 = "broken.png";

    private Texture coche;
    private float posX, posY;
    private int lado, tipo;

    public Coches(float X, float Y, int l) {
        tipo = 1 + (int) (Math.random()* 4);
        posX = X;
        posY = Y;
        lado = l;

        if(tipo == 1){
            coche = new Texture(IMAGEN1);
        }
        else if(tipo == 2){
            coche = new Texture(IMAGEN2);
        }
        else if(tipo == 3){
            coche = new Texture(IMAGEN3);
        }
        else if(tipo == 4){
            coche = new Texture(IMAGEN4);
        }
    }

    public Coches(Coches otra) {
        tipo = 1 + (int) (Math.random()* 4);
        posX = otra.getPosX();
        posY = otra.getPosY();
        lado = otra.getLado();

        if(tipo == 1){
            coche = new Texture(IMAGEN1);
        }
        else if(tipo == 2){
            coche = new Texture(IMAGEN2);
        }
        else if(tipo == 3){
            coche = new Texture(IMAGEN3);
        }
        else if(tipo == 4){
            coche = new Texture(IMAGEN4);
        }
    }

    public void draw(SpriteBatch sb){
        sb.begin();
        sb.draw(coche, posX, posY, lado, lado);
        sb.end();
    }

    //Colisiones
    public boolean colisiona(Jugador jugador) {
        //Columnas
        boolean resultado,colisionX,colisionY;

        colisionX = (Math.abs(posX - jugador.getPosX()) <= (lado));
        colisionY = (Math.abs(posY - jugador.getPosY()) <= (lado));
        resultado = colisionX && colisionY;

        return resultado;
    }

    public void moverse(int velocidad) {
        if (tipo == 4){
            posX -= velocidad;
        }
        else{
            posX -= velocidad / 2;
        }

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
        coche.dispose();
    }
}
