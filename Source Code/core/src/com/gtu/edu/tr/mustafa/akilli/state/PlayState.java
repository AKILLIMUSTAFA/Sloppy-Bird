package com.gtu.edu.tr.mustafa.akilli.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.gtu.edu.tr.mustafa.akilli.score.HighScoreHandler;
import com.gtu.edu.tr.mustafa.akilli.sprites.Bird;
import com.gtu.edu.tr.mustafa.akilli.sprites.Tube;

import java.util.Iterator;

/**
 * Created by MustafaMonster on 4.02.2016.
 */
public class PlayState extends State{
    private static final int TUBE_SPACING = Gdx.graphics.getWidth()/3;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -Gdx.graphics.getHeight()/13;

    private Bird bird;
    private Array<Tube> tubes;
    private Texture background;
    private Texture groundTexture;
    private Vector2 groundPosition1,groundPosition2;

    private Sound fallDownSoundEffect;
    private Sound scoreSoundEffect;
    private Sound haydaaSoundEffect;

    private int score;
    private HighScoreHandler highScoreHandler;
    private long lastTime;
    private long farkTime;


    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font12;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()/3);
        camera.setToOrtho(false,(int) (Gdx.graphics.getWidth()/2),(int) (Gdx.graphics.getHeight()/2));//zoom icin

        tubes = new Array<Tube>();

        for(int i=0; i <= TUBE_COUNT; ++i){
            tubes.add(new Tube((i+1)*(TUBE_SPACING)+ Tube.TUBE_WIDTH));
        }

        background = new Texture("background.png");
        groundTexture = new Texture("ground.png");
        groundPosition1 = new Vector2(camera.position.x-camera.viewportWidth/2,GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((camera.position.x-camera.viewportWidth/2)+groundTexture.getWidth(), GROUND_Y_OFFSET);

        fallDownSoundEffect = Gdx.audio.newSound(Gdx.files.internal("fallDownSound.mp3"));
        scoreSoundEffect = Gdx.audio.newSound(Gdx.files.internal("scoreSound.mp3"));
        haydaaSoundEffect = Gdx.audio.newSound(Gdx.files.internal("haydaaSound.mp3"));


        //Score********************************************************
        highScoreHandler.load();
        score = 0;

        parameter.size = Gdx.graphics.getWidth()/34;
        parameter.color = Color.BLACK;
        font12 = generator.generateFont(parameter); // font size 12 pixels

        lastTime=0;
        farkTime=TimeUtils.nanoTime()-lastTime;
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);

        //camera kusu takip eder
        camera.position.x = bird.getPosition().x + Gdx.graphics.getWidth()/6;

        //ekrandan cikan tubu sona ekle
        for (int i=0; i < tubes.size; ++i) {
            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPositionTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * (TUBE_COUNT)));
            }

            if(tube.collides(bird.getBoundsBird())){
                fallDownSoundEffect.play(0.5f);
                haydaaSoundEffect.play(1f);
                dispose();
                gameStateManager.set(new MenuState(gameStateManager));
            }

            farkTime= TimeUtils.nanoTime()-lastTime;

            //score update
            if(bird.getPosition().x > tube.getPositionBottomTube().x &&
                    bird.getPosition().x < tube.getPositionBottomTube().x+(int) (Gdx.graphics.getWidth()/9.2)    &&
                    farkTime>600000000){
                scoreSoundEffect.play(0.5f);
                score++;
                lastTime = TimeUtils.nanoTime(); //uretildigi zaman
            }
        }

        updateGround();

        //Bird yere dokunursa
        if(bird.getPosition().y <= groundTexture.getHeight() +GROUND_Y_OFFSET){
            fallDownSoundEffect.play(0.5f);
            haydaaSoundEffect.play(1f);
            dispose();
            gameStateManager.set(new MenuState(gameStateManager));
        }


        //update high score
        if(score>highScoreHandler.getHighScore())
            highScoreHandler.setHighScore(score);

        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);//zoom icin
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth/2),0,
                (int) (Gdx.graphics.getWidth()/1.6),(int) (Gdx.graphics.getHeight()/1.5));
        spriteBatch.draw(bird.getBirdTexture(),bird.getPosition().x,bird.getPosition().y,
                Gdx.graphics.getWidth()/14,Gdx.graphics.getHeight()/33);

        for(Tube tube : tubes){
            spriteBatch.draw(tube.getTopTube(),tube.getPositionTopTube().x,tube.getPositionTopTube().y,
                    (int) (Gdx.graphics.getWidth()/9.2), (int) (Gdx.graphics.getHeight()/2.5));


            spriteBatch.draw(tube.getBottomTube(),tube.getPositionBottomTube().x,tube.getPositionBottomTube().y,
                    (int) (Gdx.graphics.getWidth()/9.2), (int) (Gdx.graphics.getHeight()/2.5));
        }


        spriteBatch.draw(groundTexture,groundPosition1.x,groundPosition1.y,
                (int) (Gdx.graphics.getWidth()/1.4), (int) (Gdx.graphics.getHeight()/7.1));
        spriteBatch.draw(groundTexture,groundPosition2.x,groundPosition2.y,
                (int) (Gdx.graphics.getWidth()/1.4), (int) (Gdx.graphics.getHeight()/7.1));

        font12.draw(spriteBatch, "Score: "+ score, bird.getPosition().x, (int) (Gdx.graphics.getHeight()/30));// text
        font12.draw(spriteBatch, "HigScore: "+ HighScoreHandler.getHighScore(), bird.getPosition().x, (int) (Gdx.graphics.getHeight()/60));// text

        spriteBatch.end();

    }


    private void updateGround(){
        if(camera.position.x - (camera.viewportWidth/2)> groundPosition1.x + groundTexture.getWidth())
            groundPosition1.add(groundTexture.getWidth()*2,0);
        if(camera.position.x - (camera.viewportWidth/2)> groundPosition2.x + groundTexture.getWidth())
            groundPosition2.add(groundTexture.getWidth()*2,0);
    }

    @Override
    public void dispose() {
        bird.dispose();
        background.dispose();
        groundTexture.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
        generator.dispose();
        scoreSoundEffect.dispose();
    }


}
