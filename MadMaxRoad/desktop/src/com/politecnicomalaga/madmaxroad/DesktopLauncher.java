package com.politecnicomalaga.madmaxroad;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.politecnicomalaga.madmaxroad.GdxMadMaXRoad;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(1000, 800);
		config.setTitle("Mad Max Road");
		new Lwjgl3Application(new GdxMadMaXRoad(), config);
	}
}
