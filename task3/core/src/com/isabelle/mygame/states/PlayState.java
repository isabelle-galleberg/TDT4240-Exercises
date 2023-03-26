package com.isabelle.mygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.isabelle.mygame.sprites.Helicopter;

public class PlayState extends State{
    private Texture background;
    private Helicopter helicopter, helicopter2, helicopter3;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.jpeg");
        helicopter = new Helicopter(0, 0);
        helicopter2 = new Helicopter(50,400);
        helicopter3 = new Helicopter(400, 200);
    }

    @Override
    protected void handleInput() {
        helicopter.checkCollision(helicopter2);
        helicopter.checkCollision(helicopter3);
        helicopter2.checkCollision(helicopter3);
    }

    @Override
    public void update(float dt) {
        handleInput();
        helicopter.update(dt);
        helicopter2.update(dt);
        helicopter3.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(helicopter.getHelicopter(), helicopter.getPosition().x, helicopter.getPosition().y);
        sb.draw(helicopter2.getHelicopter(), helicopter2.getPosition().x, helicopter2.getPosition().y);
        sb.draw(helicopter3.getHelicopter(), helicopter3.getPosition().x, helicopter3.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        helicopter.dispose();
        helicopter2.dispose();
        helicopter3.dispose();
    }
}
