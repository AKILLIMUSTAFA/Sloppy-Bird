package com.gtu.edu.tr.mustafa.akilli;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.gtu.edu.tr.mustafa.akilli.state.GameStateManager;
import com.gtu.edu.tr.mustafa.akilli.state.MenuState;


public class SloppyBird extends ApplicationAdapter {
	public static final int WIDTH_DESKTOP=480;
	public static final int HEIGHT_DESKTOP=800;
	public static final String TITLE ="Sloppy Bird";

	private GameStateManager gameStateManager;
	private MenuState menuState;
	private SpriteBatch batch;

	private Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		menuState = new MenuState(gameStateManager);
		gameStateManager.push(menuState);

		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);
	}

	@Override
	public void dispose() {
		batch.dispose();
		menuState.dispose();
		music.dispose();
		super.dispose();
	}

}

