package com.gtu.edu.tr.mustafa.akilli.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by MustafaMonster on 4.02.2016.
 */
public class MenuState extends State{
    private Texture background;
    private Texture playButton;
    private Texture infoButton;
    private Texture closeButton;
    private int scoreX;
    private int scoreY;
    BitmapFont mFont;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("background.png");
        playButton = new Texture("playbutton.png");
        infoButton = new Texture("infobutton.png");
        closeButton = new Texture("closeButton.png");
        scoreX = 0;
        scoreY = 0;
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());//zoom
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {

            //Game Button
            if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/6 &&
                    Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + Gdx.graphics.getWidth()/6 &&
                    Gdx.input.getY() < Gdx.graphics.getHeight() / 2 &&
                    Gdx.input.getY() > Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight()/10) {

                gameStateManager.set(new PlayState(gameStateManager));
                dispose();
            }

            //Info Button
            if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/6 &&
                    Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + Gdx.graphics.getWidth()/6 &&
                    Gdx.input.getY() > Gdx.graphics.getHeight() / 2 &&
                    Gdx.input.getY() < Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight()/10 + 20) {
                dispose();
                gameStateManager.set(new InfoState(gameStateManager));

            }

            //Exit Button
            if(Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/6 &&
                    Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + Gdx.graphics.getWidth()/6 &&
                    Gdx.input.getY() > Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight()/10 + 20 &&
                    Gdx.input.getY() < Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight()/10 + 20 + Gdx.graphics.getHeight()/10 + 20){
                dispose();
                Gdx.app.exit();
            }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);//zoom icin
        spriteBatch.begin();
        spriteBatch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteBatch.draw(playButton,Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/2,
                Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/10);
        spriteBatch.draw(infoButton,Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/10-20,
                Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/10);
        spriteBatch.draw(closeButton,Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/2-(Gdx.graphics.getHeight()/10+20)*2,
                Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/10);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        infoButton.dispose();
        closeButton.dispose();
    }
}
