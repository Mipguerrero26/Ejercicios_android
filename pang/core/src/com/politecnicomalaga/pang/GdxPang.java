package com.politecnicomalaga.pang;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.politecnicomalaga.pang.managers.GameManager;
import com.politecnicomalaga.pang.managers.ScreensManager;

public class GdxPang extends Game {
	SpriteBatch batch;
	OrthographicCamera camera;
	ScreensManager scMg;
	Screen activeScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		scMg = ScreensManager.getSingleton();
		camera = new OrthographicCamera();
		activeScreen = scMg.getScreen(this, ScreensManager.Screens.SPLASH);
		this.setScreen(activeScreen);

		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		GameManager.getSingleton().addDelta();

		batch.begin();
			this.getScreen().render(Gdx.graphics.getDeltaTime());
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		activeScreen.dispose();
	}
}
