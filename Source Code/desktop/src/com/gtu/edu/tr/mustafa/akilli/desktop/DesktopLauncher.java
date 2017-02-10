package com.gtu.edu.tr.mustafa.akilli.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gtu.edu.tr.mustafa.akilli.SloppyBird;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SloppyBird.WIDTH_DESKTOP;
		config.height = SloppyBird.HEIGHT_DESKTOP;
		config.title = SloppyBird.TITLE;
		new LwjglApplication(new SloppyBird(), config);
	}
}
