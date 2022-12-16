package com.politecnicomalaga.madmaxroad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ConjuntoCoches {
    //Atributos
    Array<Coches> conjuntoCoches;
    Coches coche;

    //Métodos
    public ConjuntoCoches() {
        conjuntoCoches = new Array<Coches>();

        coche = new Coches(Gdx.graphics.getWidth(), (int) (Math.random() * (Gdx.graphics.getHeight() - 88) + 8), 80);
        conjuntoCoches.add(coche);
    }

    public void moverse( int velFondoX) {
        for (int i = 0; i < conjuntoCoches.size; i++){
            conjuntoCoches.get(i).moverse(velFondoX);
        }
    }

    //crecer
    public void crecer( int velFondoX) {
        Coches nuevaCoche;
        nuevaCoche = new Coches(Gdx.graphics.getWidth(), (int) (Math.random() * (Gdx.graphics.getHeight() - 88) + 8), 80);
        nuevaCoche.moverse(velFondoX);
        conjuntoCoches.add(nuevaCoche);
    }

    public void pintarse(SpriteBatch sb) {
        for (int i = 0; i < conjuntoCoches.size; i++){
            conjuntoCoches.get(i).draw(sb);;
        }
    }

    public Array<Coches> getCocheActivo() {
        return conjuntoCoches;
    }

    public void dispose() {
        for (Coches c: conjuntoCoches) {
            c.dispose();
        }
    }

}
