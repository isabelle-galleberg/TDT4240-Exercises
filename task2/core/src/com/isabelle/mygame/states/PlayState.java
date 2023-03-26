package com.isabelle.mygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.isabelle.mygame.sprites.Helicopter;

public class PlayState extends State{
    private Texture background;
    private Helicopter helicopter;
    private BitmapFont heliPosition;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.jpeg");
        helicopter = new Helicopter(0, 0);
        heliPosition = new BitmapFont();
    }

    @Override
    protected void handleInput() {
        // drag and drop helicopter with cursor
        if(Gdx.input.isTouched()) {
            helicopter.moveTo(Gdx.input.getX() - helicopter.getHelicopter().getWidth() / 2, (Gdx.graphics.getHeight() - Gdx.input.getY()) - helicopter.getHelicopter().getHeight() / 2);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        helicopter.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(helicopter.getHelicopter(), helicopter.getPosition().x, helicopter.getPosition().y);
        heliPosition.draw(sb, helicopter.getPosition().toString(), 10, Gdx.graphics.getHeight()-10);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        helicopter.dispose();
    }
}
