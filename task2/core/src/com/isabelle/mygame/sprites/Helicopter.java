package com.isabelle.mygame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Helicopter {
    private Rectangle screenSize;
    private Texture helicopterTexture;
    private Sprite helicopter;
    private Vector2 position;

    public Helicopter(int x, int y) {
        screenSize = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        helicopterTexture = new Texture("helicopter.png");
        position = new Vector2(x, y);
        helicopter = new Sprite(helicopterTexture);
        helicopter.flip(true, false);
    }

    public void update() {
        if (position.x < screenSize.x) {
            position.x = screenSize.x;
        } else if (position.x > Gdx.graphics.getWidth() - helicopter.getWidth()) {
            position.x = Gdx.graphics.getWidth() - helicopter.getWidth();
        }
        if (position.y < screenSize.y) {
            position.y = screenSize.y;
        } else if (position.y > Gdx.graphics.getHeight() - helicopter.getHeight()) {
            position.y = Gdx.graphics.getHeight() - helicopter.getHeight();
        }
    }

    public void moveTo(float x, float y) {
        position.x = x;
        position.y = y;
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