package com.politecnicomalaga;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Macedonia {
    //Atributos
    Array<Frutas> macedonia;
    Frutas fruta;

    //Métodos
    public Macedonia() {
        macedonia = new Array<Frutas>();

        fruta = new Frutas((int) (Math.random() * (Gdx.graphics.getWidth() - 88) + 8), Gdx.graphics.getHeight(), 60);
        macedonia.add(fruta);
    }

    public void moverse() {
        for (int i = 0; i < macedonia.size; i++){
            macedonia.get(i).moverse();
        }
    }

    //crecer
    public void crecer() {
        Frutas nuevaFruta;
        nuevaFruta = new Frutas((int) (Math.random() * (Gdx.graphics.getWidth() - 88) + 8), Gdx.graphics.getHeight(), 60);
        nuevaFruta.moverse();
        macedonia.add(nuevaFruta);
    }

    public void pintarse(SpriteBatch sb) {
        for (int i = 0; i < macedonia.size; i++){
            macedonia.get(i).draw(sb);
        }
    }

    public Array<Frutas> getFrutaActiva() {
        return macedonia;
    }

    public void dispose() {
        for (Frutas c: macedonia) {
            c.dispose();
        }
    }

}
