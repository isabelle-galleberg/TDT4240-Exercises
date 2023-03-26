package com.isabelle.mygame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Helicopter {
    private static final int SPEED = 100;
    private Rectangle screenSize;
    private Texture helicopterTexture;
    private Sprite helicopter;
    private Vector2 position;
    private int verticalSpeed = SPEED;
    private int horizontalSpeed = SPEED;


    public Helicopter(int x, int y){
        screenSize = new Rectangle(0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        helicopterTexture = new Texture("helicopter.png");
        position = new Vector2(x, y);
        helicopter = new Sprite(helicopterTexture);
        helicopter.flip(true, false);
    }

    public void update(float dt){
        if (position.x < 0 || position.x + helicopterTexture.getWidth() >= screenSize.width) {
            horizontalSpeed = horizontalSpeed * -1;
            helicopter.flip(true, false);
        }
        if (position.y < 0 || position.y + helicopterTexture.getHeight() >= screenSize.height) {
            verticalSpeed = verticalSpeed * -1;
        }
        position.add(horizontalSpeed * dt, verticalSpeed * dt);

    }

    public Sprite getHelicopter() {
        return helicopter;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose() {
        helicopterTexture.dispose();
    }
}
