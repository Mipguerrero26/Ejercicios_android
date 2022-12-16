package com.politecnicomalaga;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Contador {

    //Constantes

    private static final String NOMBRE_SPRITES = "digito";
    private static final String EXT_SPRITES =".png";

    //Almacenaremos números de base en un array list "digitos"
    protected ArrayList<Texture> listaDigitos;

    //Almacenaremos los números necesarios para pintar el número asignado en otro arraylist
    protected ArrayList<Texture> listaMostrada;

    protected float fPosX;
    protected float fPosY;
    protected float fAncho;

    protected int iValorAlmacenado;

    //CONSTRUCTORES
    public Contador(float pX, float pY, float nAncho) {
        fPosX = pX;
        fPosY = pY;
        fAncho = nAncho;

        //creamos el array de base y el array a pintar (con el 0)
        listaDigitos = new ArrayList<Texture>();
        for (int i = 0;i<10;i++) {
            Texture nuevoDigito = new Texture(NOMBRE_SPRITES + String.valueOf(i) + EXT_SPRITES);
            listaDigitos.add(nuevoDigito);
        }

        listaMostrada = new ArrayList<Texture>();
        listaMostrada.add(listaDigitos.get(0));
        iValorAlmacenado = 0;

    }

    //Resto de comportamientos

    //Método pintarse
    public void pintarse(SpriteBatch miSB) {
        float pX,pY;
        pX = fPosX;
        pY = fPosY;
        for (Texture digito :listaMostrada) {
            miSB.draw(digito,pX,pY,fAncho,fAncho);
            pX += fAncho;
        }

    }

    //Asignar el número:
    public void setData(int iValor) {

        if (iValor > 0) {
            iValorAlmacenado = iValor;
            listaMostrada.clear();
            String sNumero = String.valueOf(iValor);
            for (int i=0; i<sNumero.length();i++) {
                String digito = String.valueOf(sNumero.charAt(i));

                listaMostrada.add(listaDigitos.get(Integer.valueOf(digito)));
            }
        }
    }

    //Método incremento
    public void incrementa(int iValor) {
        if (iValor > 0) {
            iValorAlmacenado+= iValor;
            listaMostrada.clear();
            String sNumero = String.valueOf(iValorAlmacenado);
            for (int i=0; i<sNumero.length();i++) {
                String digito = String.valueOf(sNumero.charAt(i));

                listaMostrada.add(listaDigitos.get(Integer.valueOf(digito)));
            }
        }
    }

    //Método dispose. Para eliminar los recursos
    public void dispose() {
        for ( Texture t :listaDigitos) {
            t.dispose();
        }
    }
}
