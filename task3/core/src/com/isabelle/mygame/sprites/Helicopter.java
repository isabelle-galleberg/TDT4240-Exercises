package com.isabelle.mygame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Helicopter {
    private Rectangle screenBounds;
    private Texture texture;
    private Animation heliAnimation;
    private Vector2 position;
    private float verticalSpeed;
    private float horizontalSpeed;

    public Helicopter(int x, int y) {
        screenBounds =  new Rectangle(0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        texture = new Texture("heliAnimation.png");
        heliAnimation = new Animation(new TextureRegion(texture), 4, 0.1f);
        position = new Vector2(x, y);
        verticalSpeed = (float) Math.floor(Math.random() * 500);
        horizontalSpeed = (float) Math.floor(Math.random() * 500);
    }

    public void update(float dt){
        heliAnimation.update(dt);
        if (position.x < 0 || position.x + getHelicopter().getRegionWidth() >= screenBounds.width) {
            horizontalSpeed *= -1;
        }
        if (position.y < 0 || position.y + getHelicopter().getRegionHeight() >= screenBounds.height) {
            verticalSpeed *= -1;
        }
        position.add(horizontalSpeed * dt, verticalSpeed * dt);
    }

    public Vector2 getPosition() {
        return position;
    }

    public TextureRegion getHelicopter() {
        return heliAnimation.getFrame();
    }

   public Rectangle getHelicopterBounds() {
        return new Rectangle(position.x, position.y, getHelicopter().getRegionWidth(), getHelicopter().getRegionHeight());
    }

    public void checkCollision(Helicopter helicopter) {
        if (helicopter.getHelicopterBounds().overlaps(getHelicopterBounds())){
            horizontalSpeed *= -1;
            verticalSpeed *= -1;
            helicopter.horizontalSpeed *= -1;
            helicopter.verticalSpeed *= -1;
        }
    }
    public void dispose() {
        texture.dispose();
    }
}
