package com.politecnicomalaga;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GdxFruitMonkey extends ApplicationAdapter {
	SpriteBatch batch;
	Mono mono;
	Macedonia macedonia;
	Contador contador;

	float posXMono, posYMono ;
	int tiempo, lado;
	int direccion;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		lado = 60;
		posXMono = (Gdx.graphics.getWidth() / 2) - (lado / 2);
		posYMono = 30;
		mono = new Mono(posXMono,posYMono,lado);
		macedonia = new Macedonia();
		contador = new Contador(20f,Gdx.graphics.getHeight() - lado, lado);
		contador.setData(0);
		direccion = 2;
		tiempo = 0;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0,1);

		int pixelX;

		//Jugando

		//Comprobamos entrada
		if (Gdx.input.justTouched()) {
			//han tocado la pantalla
			pixelX = Gdx.input.getX();

			if (pixelX > 2 * (Gdx.graphics.getWidth() / 3)) {
				direccion = 1;
			}
			else if (pixelX < Gdx.graphics.getWidth() / 3) {
				direccion = 3;
			}
			else{
				direccion = 2;
			}

		}

		tiempo++;

		if (tiempo % 1 == 0) {
			macedonia.moverse();

		}
		if (tiempo % 100 == 0) {
			macedonia.crecer();

		}

		if (direccion == 1 && mono.getPosX() + lado < Gdx.graphics.getWidth()) {
			mono.moverse(Mono.Direccion.DERECHA);
		}
		else if (direccion == 3 && mono.getPosX() > 0) {
			mono.moverse(Mono.Direccion.IZQUIERDA);
		}
		else{
			mono.moverse(Mono.Direccion.QUIETO);
		}

		Array<Frutas> frutaActiva = macedonia.getFrutaActiva();
		for (int i = 0; i < frutaActiva.size; i++) {
			if (frutaActiva.get(i).colisiona(mono)) {
				contador.incrementa(1);
				frutaActiva.removeIndex(i).dispose();
				i--;
			}
		}

		//Pintamos
		batch.begin();
		contador.pintarse(batch);
		batch.end();

		macedonia.pintarse(batch);
		mono.draw(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		mono.dispose();
		macedonia.dispose();
		contador.dispose();
	}
}
