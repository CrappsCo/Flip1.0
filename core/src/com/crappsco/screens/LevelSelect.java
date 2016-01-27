package com.crappsco.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crappsco.GameObjects.Tile;
import com.crappsco.flip.Flip;

/**
 * Created by Ray on 2016-01-26.
 */
public class LevelSelect implements Screen {
    public Viewport viewport;
    public OrthographicCamera camera;
    public final float WIDTH = 1080;
    public final float HEIGHT = 1920;

    private float runTime = 0;

    public Vector3 touchPoint;

    public static Tile[][] currentLevel;
    public static int rowNum, colNum;


    final Flip game;

    public LevelSelect(Flip game) {

        this.game = game;

        //Set Camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        camera = new OrthographicCamera(25 * aspectRatio, 25);
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        viewport = new StretchViewport(WIDTH, HEIGHT, camera);
        viewport.apply();

        //Set ProjectionMatrix
        game.batcher.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime += delta;

        //****************************************UPDATE SECTION****************************************
        //Set current level
        if (Gdx.input.justTouched()) {
            touchPoint = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            currentLevel = Level1();
            game.setScreen(new GamePanel(game));
            dispose();
        }


        //****************************************RENDER SECTION****************************************
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batcher.begin();

        game.font.draw(game.batcher, "Level 1", 100, 150);

        game.batcher.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
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

    //LEVEL FUNCTIONS

    public static Tile[][] Level1() {
        rowNum = 5;
        colNum = 5;
        Tile[][] level1 = new Tile[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                level1[i][j] = new Tile(120f * i + 240f, 120f * j + 100f, 0);
            }
        }
        return level1;
    }
}
