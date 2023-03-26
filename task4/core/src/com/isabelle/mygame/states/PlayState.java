package com.isabelle.mygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.isabelle.mygame.sprites.Ball;
import com.isabelle.mygame.sprites.Paddle;

public class PlayState extends State{
    private Texture background;
    private Paddle player1, player2;
    private Ball ball;
    private BitmapFont font;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.jpg");
        player1 = new Paddle(0, Gdx.graphics.getHeight()/2 - Paddle.PADDLE_HEIGHT/2);
        player2 = new Paddle(Gdx.graphics.getWidth()- Paddle.PADDLE_WIDTH, Gdx.graphics.getHeight()/2 - Paddle.PADDLE_HEIGHT/2);
        ball = new Ball(Gdx.graphics.getWidth()/2 - Ball.BALL_RADIUS,Gdx.graphics.getHeight()/2 - Ball.BALL_RADIUS);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
    }

    private void endGame(){
        if (player1.getPoints() >= 21 || player2.getPoints() >= 21)  {
            gsm.set(new WinState(gsm));
        }
    }

    private void movePaddles(){
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player1.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player1.moveUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player2.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player2.moveUp();
        }
    }

    @Override
    protected void handleInput() {
        endGame();
        movePaddles();
        // handle paddle hits
        ball.hitBall(player1);
        ball.hitBall(player2);
        // handle points
        if (ball.scorePoint(player1)) {
            player1.scorePoint();
        }
        if (ball.scorePoint(player2)) {
            player2.scorePoint();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        ball.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(player1.getPaddle(), player1.getPosition().x, player1.getPosition().y);
        sb.draw(player2.getPaddle(), player2.getPosition().x, player2.getPosition().y);
        sb.draw(ball.getBall(), ball.getPosition().x, ball.getPosition().y);
        font.draw(sb, String.valueOf(player1.getPoints()), 50, Gdx.graphics.getHeight()-50);
        font.draw(sb, String.valueOf(player2.getPoints()), player2.getPosition().x-50, Gdx.graphics.getHeight()-50);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        player1.dispose();
        player2.dispose();
        ball.dispose();
        font.dispose();
    }
}
