package com.gtu.edu.tr.mustafa.akilli.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by MustafaMonster on 4.02.2016.
 */
public class InfoState extends State{

    private Texture background;
    private Texture backMenuButton;
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font12;

    protected InfoState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("background.png");
        backMenuButton = new Texture("backMenuButton.png");

        parameter.size = Gdx.graphics.getWidth()/17;
        font12 = generator.generateFont(parameter); // font size 12 pixels
    }

    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched()) {

            if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/6 &&
                    Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + Gdx.graphics.getWidth()/6 &&
                    Gdx.input.getY() > Gdx.graphics.getHeight() / 2 &&
                    Gdx.input.getY() < Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight()/10 + 20) {
                gameStateManager.set(new MenuState(gameStateManager));
                dispose();
            }

        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.begin();
        spriteBatch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteBatch.draw(backMenuButton,Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/10-20,
                Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/10);
        font12.draw(spriteBatch, "Version: v1.0.0MA: ", Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2+Gdx.graphics.getHeight()/40);// text
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        backMenuButton.dispose();
        generator.dispose();
    }
}
