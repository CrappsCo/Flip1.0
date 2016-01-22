package com.crappsco.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.crappsco.GameWorld.GameRenderer;
import com.crappsco.GameWorld.GameUpdater;
import com.crappsco.FrameworkHelpers.InputHandler;

/**
 * Created by Ray on 2016-01-20.
 */
public class GamePanel implements Screen {

    private GameUpdater updater;
    private GameRenderer renderer;

    private float runTime = 0;

    public GamePanel(){
        //separate classes for updating and rendering
        //do we even need updating....
        updater = new GameUpdater();
        renderer = new GameRenderer(updater);
        //setting InputHandler
        Gdx.input.setInputProcessor(new InputHandler(renderer));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        //updater.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        renderer.viewport.update(width, height);
        renderer.camera.position.set(renderer.camera.viewportWidth / 2, renderer.camera.viewportHeight / 2, 0);
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
