package com.isabelle.mygame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Paddle {
    public static final int PADDLE_HEIGHT = 118;
    public static final int PADDLE_WIDTH = 45;
    private Texture paddle;
    private Vector2 position;
    private int points;

    public Paddle(int x, int y) {
        this.paddle = new Texture("dahls.png");
        this.position = new Vector2(x, y);
        this.points = 0;
    }

    public void moveUp(){
        if (getPosition().y < Gdx.graphics.getHeight() - getPaddle().getHeight()){
            position.y += 10;
        }
    }

    public void moveDown(){
        if (getPosition().y > 0){
            position.y -= 10;
        }
    }

    public void scorePoint(){
        points++;
    }

    public Rectangle getObjectBounds() {
        return new Rectangle(getPosition().x, this.getPosition().y, getPaddle().getWidth(), getPaddle().getHeight());
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getPaddle() {
        return paddle;
    }

    public int getPoints(){
        return points;
    }

    public void dispose() {
        paddle.dispose();
    }
}
