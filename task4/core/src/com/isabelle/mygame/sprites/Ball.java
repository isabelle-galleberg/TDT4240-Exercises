package com.isabelle.mygame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ball {
    public static final int BALL_RADIUS = 13;
    private static final int START_SPEED = 300;
    private static final ArrayList<Integer> direction = new ArrayList<>(Arrays.asList(1,-1));
    private Texture ball;
    private Vector2 position;
    private float verticalSpeed;
    private float horizontalSpeed;
    private int hitCounter;

    public Ball(int x, int y) {
        this.ball = new Texture("ball.png");
        this.position = new Vector2(x, y);
        this.verticalSpeed = (float) Math.floor(Math.random() * START_SPEED) * getRandomDirection();
        this.horizontalSpeed = START_SPEED;
        this.hitCounter = 0;
    }

    public void update(float dt){
        hitTopBottom();
        position.add(horizontalSpeed * dt, verticalSpeed * dt);
    }

    // ball hits top or bottom of screen
    private void hitTopBottom() {
        if (getPosition().y <= 0 || getPosition().y >= Gdx.graphics.getHeight() - BALL_RADIUS) {
            verticalSpeed = verticalSpeed * -1;
        }
    }

    // ball hits paddle
    public void hitBall(Paddle paddle) {
        if (paddle.getObjectBounds().overlaps(getObjectBounds())){
            hitCounter++;
            // increase speed after 3 hits
            if (hitCounter % 3 == 0){
                horizontalSpeed *= 1.1;
            }
            horizontalSpeed *= -1;
            verticalSpeed = (float) Math.floor(Math.random() * START_SPEED) * -1;
        }

    }

    private void newRound() {
        hitCounter = 0;
        position.x = Gdx.graphics.getWidth() / 2 - Ball.BALL_RADIUS;
        position.y = Gdx.graphics.getHeight() / 2 - Ball.BALL_RADIUS;
        verticalSpeed *= getRandomDirection();
    }

    public boolean scorePoint(Paddle paddle) {
        // player 1 scores point
        if (getPosition().x > Gdx.graphics.getWidth() - Paddle.PADDLE_WIDTH - BALL_RADIUS && paddle.getPosition().x < Gdx.graphics.getWidth()/2){
            horizontalSpeed = -START_SPEED;
            newRound();
            return true;
        }
        // player 2 scores point
        else if (getPosition().x < Paddle.PADDLE_WIDTH-10 && paddle.getPosition().x > Gdx.graphics.getWidth()/2) {
            horizontalSpeed = START_SPEED;
            newRound();
            return true;
        }
       return false;
    }

    private int getRandomDirection() {
        return direction.get(new Random().nextInt(direction.size()));
    }

    private Rectangle getObjectBounds() {
        return new Rectangle(getPosition().x, this.getPosition().y, getBall().getWidth(), getBall().getHeight());
    }

    public Texture getBall() {
        return ball;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose() {
        ball.dispose();
    }
}
