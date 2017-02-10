package com.gtu.edu.tr.mustafa.akilli.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MustafaMonster on 4.02.2016.
 */
public class Tube {
    private Texture topTube;
    private Texture bottomTube;
    private Vector2 positionTopTube;
    private Vector2 positionBottomTube;

    private Rectangle boundsTop;
    private Rectangle boundsBottom;

    public static final int TUBE_WIDTH = (int) (Gdx.graphics.getWidth()/10);
    public static final int TUBE_HEIGHT = (int) (Gdx.graphics.getHeight()/2.5);

    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");

        //rand+TUBE gap+lowest opening
        positionTopTube = new Vector2(x, MathUtils.random(0,Gdx.graphics.getHeight()/6)+Gdx.graphics.getHeight()/8+Gdx.graphics.getHeight()/7);
        positionBottomTube = new Vector2(x,positionTopTube.y-Gdx.graphics.getHeight()/8-(int) (Gdx.graphics.getHeight()/2.5));

        boundsTop = new Rectangle(positionTopTube.x,positionTopTube.y,(int) (Gdx.graphics.getWidth()/9.2), (int) (Gdx.graphics.getHeight()/2.5));
        boundsBottom = new Rectangle(positionBottomTube.x,positionBottomTube.y,(int) (Gdx.graphics.getWidth()/9.2), (int) (Gdx.graphics.getHeight()/2.5));

    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionBottomTube() {
        return positionBottomTube;
    }

    public void reposition(float x){
        positionTopTube.set(x, MathUtils.random(0,Gdx.graphics.getHeight()/6)+Gdx.graphics.getHeight()/8+Gdx.graphics.getHeight()/7);
        positionBottomTube.set(x,positionTopTube.y-Gdx.graphics.getHeight()/8-(int) (Gdx.graphics.getHeight()/2.5));

        boundsTop.setPosition(positionTopTube.x,positionTopTube.y);
        boundsBottom.setPosition(positionBottomTube.x,positionBottomTube.y);
    }

    public boolean collides(Rectangle player){
        return (player.overlaps(boundsTop) || player.overlaps(boundsBottom));
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
