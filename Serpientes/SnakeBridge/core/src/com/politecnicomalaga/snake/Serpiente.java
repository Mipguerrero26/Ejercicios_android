package com.politecnicomalaga.snake;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Serpiente {

    //Atributos
    ArrayList<Cuadrado> miCuerpo;
    Cuadrado.Direccion miDireccion;

    //Métodos
    public Serpiente() {
        miCuerpo = new ArrayList<Cuadrado>();
        miDireccion = Cuadrado.Direccion.ARRIBA;

        Cuadrado cabeza = new Cuadrado(200,200,20);
        miCuerpo.add(cabeza);
    }

    //moverse
    public void moverse() {
        this.crecer(false);
        Cuadrado elemento = miCuerpo.remove(miCuerpo.size()-1);
        elemento.dispose();
    }


    //crecer
    public void crecer(boolean activo) {
        if (!activo){
            Cuadrado nuevaCabeza, cabeza;
            cabeza = miCuerpo.get(0);
            nuevaCabeza = new Cuadrado(cabeza);
            nuevaCabeza.moverse(miDireccion);

            miCuerpo.add(0,nuevaCabeza);
        }
        else{
            if (getCabezaX() == Puente.puente2.posX && getCabezaY() == Puente.puente2.posY ){
                Cuadrado nuevaCabeza, cabeza;
                cabeza = Puente.puente1;
                nuevaCabeza = new Cuadrado(cabeza);
                nuevaCabeza.moverse(miDireccion);

                miCuerpo.add(0,nuevaCabeza);
            }
            else{
                Cuadrado nuevaCabeza, cabeza;
                cabeza = Puente.puente2;
                nuevaCabeza = new Cuadrado(cabeza);
                nuevaCabeza.moverse(miDireccion);

                miCuerpo.add(0,nuevaCabeza);
            }

        }

    }

    public void pintarse(SpriteBatch sb) {
        for (int i = 0; i < miCuerpo.size(); i++){
            if (i == 0)
                miCuerpo.get(i).pintarseEsp(sb);
            else
                miCuerpo.get(i).pintarseRamd(sb);
        }
    }

    public boolean colisiona() {
        Cuadrado cabeza = miCuerpo.get(0);
        for (int i=4;i<miCuerpo.size();i++) {
            if (cabeza.colisiona(miCuerpo.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean estaDentro(int limiteMinX, int limiteMaxX, int limiteMinY, int limiteMaxY) {
        Cuadrado cabeza = miCuerpo.get(0);
        return (cabeza.getPosX()>limiteMinX && cabeza.getPosX()<limiteMaxX-cabeza.getLado() &&
                cabeza.getPosY()>limiteMinY && cabeza.getPosY()<limiteMaxY-cabeza.getLado());
    }

    public float getCabezaX() {
        return miCuerpo.get(0).getPosX();
    }

    public float getCabezaY() {
        return miCuerpo.get(0).getPosY();
    }

    public void cambiaDir(Cuadrado.Direccion nuevaDir) {

        boolean bNoCambiar = false;

        bNoCambiar = (miDireccion == Cuadrado.Direccion.ARRIBA && nuevaDir == Cuadrado.Direccion.ABAJO);
        bNoCambiar = bNoCambiar || (miDireccion == Cuadrado.Direccion.ABAJO && nuevaDir == Cuadrado.Direccion.ARRIBA);
        bNoCambiar = bNoCambiar || (miDireccion == Cuadrado.Direccion.IZQUIERDA && nuevaDir == Cuadrado.Direccion.DERECHA);
        bNoCambiar = bNoCambiar || (miDireccion == Cuadrado.Direccion.DERECHA && nuevaDir == Cuadrado.Direccion.IZQUIERDA);

        if (bNoCambiar == false){
            miDireccion = nuevaDir;
        }
    }

    public void dispose() {
        for (Cuadrado c: miCuerpo) {
            c.dispose();
        }
    }

}