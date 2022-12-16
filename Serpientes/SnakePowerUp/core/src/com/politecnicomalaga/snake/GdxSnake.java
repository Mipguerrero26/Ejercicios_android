package com.politecnicomalaga.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GdxSnake extends ApplicationAdapter {
	SpriteBatch batch;
	Texture imgInicio, imgJugando,imgFinal;
	enum Pantallas {INICIAL, JUGANDO, FINAL};

	Pantallas miPantallaActiva;
	Serpiente vibora;
	PowerUp powerUp;
	int iPasos, icontador, iContadorPowerUp;
	boolean inmortalidad, icomido;

	@Override
	public void create () {
		batch = new SpriteBatch();
		imgInicio = new Texture("pantallaInicio.png");
		imgJugando = new Texture("pantallaJugando.png");
		imgFinal = new Texture("pantallaFinal.png");
		miPantallaActiva = Pantallas.INICIAL;
		vibora = new Serpiente();
		powerUp = new PowerUp();
		iPasos = 0;
		icontador = 0;
		iContadorPowerUp = 0;
		inmortalidad = false;
		icomido = false;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		switch (miPantallaActiva) {
			case INICIAL: pantallaInicial();
				break;
			case JUGANDO: pantallaJugando();
				break;
			case FINAL: pantallaFinal();
				break;
		}

	}

	//Métodos de trabajo de cada una de las pantallas
	private void pantallaInicial() {
		batch.begin();
		batch.draw(imgInicio,0,0);
		batch.end();

		if (Gdx.input.justTouched()) {
			miPantallaActiva = Pantallas.JUGANDO;
			vibora.dispose();
			powerUp.dispose();

			vibora = new Serpiente();

			powerUp = new PowerUp();
			icontador = 0;
			icomido = false;
		}
	}

	private void pantallaJugando() {
		int pixelX, pixelY; //Los pixeles "clicados" en la pantalla
		float cabX, cabY;

		//Jugando

		//Comprobamos entrada
		if (Gdx.input.justTouched()) {
			//han tocado la pantalla
			pixelX = Gdx.input.getX();
			pixelY = Gdx.graphics.getHeight() - Gdx.input.getY();

			//Calculamos con respecto a la cabeza a donde nos dirigimos
			cabX = vibora.getCabezaX();
			cabY = vibora.getCabezaY();

			if (Math.abs(pixelX-cabX)>Math.abs(pixelY-cabY)) {
				//horizontal
				if (pixelX > cabX) {
					vibora.cambiaDir(Cuadrado.Direccion.DERECHA);
				} else {
					vibora.cambiaDir(Cuadrado.Direccion.IZQUIERDA);
				}
			} else {
				//vertical
				if (pixelY > cabY) {
					vibora.cambiaDir(Cuadrado.Direccion.ARRIBA);
				} else {
					vibora.cambiaDir(Cuadrado.Direccion.ABAJO);
				}
			}

		}
		iPasos++;
		icontador++;
		//Simulamos el mundo

		if (powerUp.colisiona(vibora.getCabezaX(), vibora.getCabezaY())){
			inmortalidad = true;
			icomido = true;
			powerUp.setPowerUpX(10000);
			powerUp.setPowerUpY(10000);
			powerUp.dispose();
			iContadorPowerUp = 600;
		}

		if (iContadorPowerUp >= 0){
			iContadorPowerUp--;
			if (iContadorPowerUp == 0){
				inmortalidad = false;
			}
		}

		if (iPasos % 10 == 0) {
			vibora.moverse();

			//Nos hemos estrellado???
			if ((vibora.colisiona() && inmortalidad == false)||
					!vibora.estaDentro(0,Gdx.graphics.getWidth(),0,Gdx.graphics.getHeight())) {
				miPantallaActiva = Pantallas.FINAL;
			}
		}

		if (iPasos == 59) {
			iPasos = 0;
			vibora.crecer();

			//Nos hemos estrellado???
			if ((vibora.colisiona() && inmortalidad == false) ||
					!vibora.estaDentro(0,Gdx.graphics.getWidth(),0,Gdx.graphics.getHeight())) {
				miPantallaActiva = Pantallas.FINAL;
			}
		}

		if (icontador == 1200 && icomido == false) {
			powerUp.setPowerUpX(10000);
			powerUp.setPowerUpY(10000);
			powerUp.dispose();

		}

		if (icontador == 3600) {
			icontador = 0;
			powerUp.reubicacion();
			icomido = false;

		}

		//Pintamos
		if (!icomido){
			powerUp.pintarse(batch);
		}

		vibora.pintarse(batch);

	}

	private void pantallaFinal() {
		batch.begin();
		batch.draw(imgFinal,0,0);
		batch.end();

		if (Gdx.input.justTouched()) {
			miPantallaActiva = Pantallas.INICIAL;
		}

	}

	@Override
	public void dispose () {
		batch.dispose();
		imgInicio.dispose();
		imgJugando.dispose();
		imgFinal.dispose();
		vibora.dispose();
		powerUp.dispose();
	}
}

