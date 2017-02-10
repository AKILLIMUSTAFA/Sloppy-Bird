package com.gtu.edu.tr.mustafa.akilli.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by MustafaMonster on 4.02.2016.
 */
public class Bird {
    private static final int GRAVITY = -Gdx.graphics.getHeight()/32;
    private static final int JUMP_HEIGHT = Gdx.graphics.getHeight()/3;
    private static final int MOVEMENT =  Gdx.graphics.getWidth()/5;
    private Vector3 position; //x,y,z konumunu tutuyor
    private Vector3 velocity; //hiz
    private Animation animationBird;
    private Texture textureAnimationBird;

    private Rectangle boundsBird;
    private Texture textureBird;

    private Sound birdFlapSoundEffect;


    public Bird(int positionX, int positionY){
        position = new Vector3(positionX,positionY,0);
        velocity = new Vector3(0,0,0);

        textureAnimationBird = new Texture("FeroAnimation.png");
        animationBird = new Animation(new TextureRegion(textureAnimationBird),3,0.5f);

        textureBird = new Texture("bird.png");

        boundsBird = new Rectangle(positionX,positionY,Gdx.graphics.getWidth()/14,Gdx.graphics.getHeight()/33);

        birdFlapSoundEffect = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

    }

    public void update(float dt){

        animationBird.update(dt);

        velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);

        velocity.scl(1/dt);
        boundsBird.setPosition(position.x,position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBirdTexture() {
        return animationBird.getFrame();
    }

    public void jump(){
        velocity.y = JUMP_HEIGHT;
        birdFlapSoundEffect.play(0.5f);
    }

    public Rectangle getBoundsBird(){return boundsBird;}

    public void dispose(){
        textureAnimationBird.dispose();
        textureBird.dispose();
        birdFlapSoundEffect.dispose();
    }
}

