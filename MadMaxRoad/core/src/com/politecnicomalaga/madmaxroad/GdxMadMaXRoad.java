package com.politecnicomalaga.madmaxroad;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GdxMadMaXRoad extends ApplicationAdapter {
	SpriteBatch batch;
	Fondo fondo;
	Jugador player;
	ConjuntoCoches conjuntoCoches;
	Contador contador;

	String fondoIMG;
	float posXPlayer, posYPlayer ;
	int tiempo, lado, velFondoX;
	int direccion;
	Boolean gameOver;

	@Override
	public void create () {
		batch = new SpriteBatch();

		fondoIMG = "road.png";
		velFondoX = 5;
		fondo = new Fondo(fondoIMG, velFondoX, 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 1050);

		lado = 80;
		posXPlayer = 0;
		posYPlayer = (Gdx.graphics.getHeight() / 2) - (lado / 2);
		player = new Jugador(posXPlayer,posYPlayer,lado);
		direccion = 2;

		conjuntoCoches = new ConjuntoCoches();

		contador = new Contador(20f,Gdx.graphics.getHeight() - lado, lado);
		contador.setData(3);

		gameOver = false;

		tiempo = 0;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		int pixelY;

		//Jugando
		if (!gameOver) {
			fondo.avanzar();

			//Comprobamos entrada
			if (Gdx.input.justTouched()) {
				//han tocado la pantalla
				pixelY = Gdx.input.getY();


				if (pixelY > 2 * (Gdx.graphics.getHeight() / 3)) {
					direccion = 1;
				} else if (pixelY < Gdx.graphics.getHeight() / 3) {
					direccion = 3;
				} else {
					direccion = 2;
				}

			}


			tiempo++;

			if (tiempo % 1 == 0) {
				conjuntoCoches.moverse(velFondoX);

			}
			if (tiempo % 100 == 0) {
				conjuntoCoches.crecer(velFondoX);

			}

			if (direccion == 1 && player.getPosY() + lado < Gdx.graphics.getHeight()) {
				player.moverse(Jugador.Direccion.ABAJO);
			} else if (direccion == 3 && player.getPosY() > 0) {
				player.moverse(Jugador.Direccion.ARRIBA);
			} else {
				player.moverse(Jugador.Direccion.QUIETO);
			}

			Array<Coches> cocheActivo = conjuntoCoches.getCocheActivo();
			for (int i = 0; i < cocheActivo.size; i++) {
				if (cocheActivo.get(i).colisiona(player)) {
					contador.incrementa(-1);
					cocheActivo.removeIndex(i).dispose();
					i--;
				}
			}
		}

		if (contador.iValorAlmacenado == 0){
			player.moverse(Jugador.Direccion.QUIETO);
			gameOver = true;
		}

		//Pintamos

		batch.begin();
		fondo.pintate(batch);
		contador.pintarse(batch);
		batch.end();
		conjuntoCoches.pintarse(batch);
		player.draw(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();
		conjuntoCoches.dispose();
		contador.dispose();
		fondo.dispose();
	}
}
