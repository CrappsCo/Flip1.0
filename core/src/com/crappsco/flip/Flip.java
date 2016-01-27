package com.crappsco.flip;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.crappsco.FrameworkHelpers.AssetLoader;
import com.crappsco.screens.MainMenu;

public class Flip extends Game {

    public SpriteBatch batcher;
    public BitmapFont font;

    @Override
    public void create() {
        //Set SpriteBatch and font here
        batcher = new SpriteBatch();
        font = new BitmapFont();

        //Load in Assets
        AssetLoader.load();

        //Set Screen
        setScreen(new MainMenu(this));
    }

    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
