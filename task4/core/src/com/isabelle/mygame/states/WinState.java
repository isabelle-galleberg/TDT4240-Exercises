package com.isabelle.mygame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Menu;

public class WinState extends State {
    private Texture background;
    private Texture playBtn;
    private BitmapFont font;

    public WinState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("beer.jpg");
        playBtn = new Texture("playBtn.png");
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(sb, "Game over!", (Gdx.graphics.getWidth()/2)-100, Gdx.graphics.getHeight()/2 + 100);
        sb.draw(playBtn,  (Gdx.graphics.getWidth()/2)-playBtn.getWidth()/2,100);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
    }
}
