package com.isabelle.mygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.isabelle.mygame.sprites.Helicopter;

public class PlayState extends State{
    private Texture background;
    private Helicopter helicopter;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.jpeg");
        helicopter = new Helicopter(0, 0);
    }

    @Override
    public void update(float dt) {
        helicopter.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(helicopter.getHelicopter(), helicopter.getPosition().x, helicopter.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        helicopter.dispose();
    }
}
