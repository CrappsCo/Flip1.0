package com.crappsco.flip;

import com.badlogic.gdx.Game;
import com.crappsco.FrameworkHelpers.AssetLoader;
import com.crappsco.FrameworkHelpers.LevelLoader;
import com.crappsco.screens.GamePanel;

public class Flip extends Game {

    @Override
    public void create() {
        AssetLoader.load();
        LevelLoader.load();
        setScreen(new GamePanel());
    }

    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
        LevelLoader.dispose();
    }
}
