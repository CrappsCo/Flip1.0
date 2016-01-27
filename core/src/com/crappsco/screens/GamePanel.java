package com.crappsco.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crappsco.FrameworkHelpers.AssetLoader;
import com.crappsco.GameObjects.Tile;
import com.crappsco.flip.Flip;


/**
 * Created by Ray on 2016-01-20.
 */
public class GamePanel implements Screen {

    public Viewport viewport;
    public OrthographicCamera camera;
    public final float WIDTH = 1080;
    public final float HEIGHT = 1920;

    private float runTime = 0;

    public Vector3 touchPoint;

    public Tile[][] currentLevel;


    final Flip game;

    public GamePanel(Flip game) {

        this.game = game;

        //Set level
        currentLevel = LevelSelect.currentLevel;

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
        if (Gdx.input.justTouched()) {
            touchPoint = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            for (int i = 0; i < LevelSelect.rowNum; i++) {
                for (int j = 0; j < LevelSelect.colNum; j++) {
                    Tile tile = currentLevel[i][j];
                    if (tile.contains(touchPoint.x, touchPoint.y)) {
                        flipCross(i, j);
                    }
                }
            }
        }


        //****************************************RENDER SECTION****************************************
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batcher.begin();

        //Drawing Background
        game.batcher.draw(AssetLoader.bg, 0, 0);

        //Drawing the tiles
        for (int i = 0; i < LevelSelect.rowNum; i++) {
            for (int j = 0; j < LevelSelect.colNum; j++) {
                Tile tile = currentLevel[i][j];
                if (tile.face == 0) {
                    game.batcher.draw(AssetLoader.bluecircle, tile.getX(), tile.getY());
                } else {
                    game.batcher.draw(AssetLoader.redcircle, tile.getX(), tile.getY());
                }
            }
        }
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


    //FLIP FUNCTIONS

    public void flipCross(int i, int j) {
        if (i + 1 < LevelSelect.rowNum)
            currentLevel[i + 1][j] = currentLevel[i + 1][j].flipTile(currentLevel[i + 1][j].getX(), currentLevel[i + 1][j].getY(), currentLevel[i + 1][j].getFace());
        if (i - 1 >= 0)
            currentLevel[i - 1][j] = currentLevel[i - 1][j].flipTile(currentLevel[i - 1][j].getX(), currentLevel[i - 1][j].getY(), currentLevel[i - 1][j].getFace());
        if (j + 1 < LevelSelect.colNum)
            currentLevel[i][j + 1] = currentLevel[i][j + 1].flipTile(currentLevel[i][j + 1].getX(), currentLevel[i][j + 1].getY(), currentLevel[i][j + 1].getFace());
        if (j - 1 >= 0)
            currentLevel[i][j - 1] = currentLevel[i][j - 1].flipTile(currentLevel[i][j - 1].getX(), currentLevel[i][j - 1].getY(), currentLevel[i][j - 1].getFace());
        currentLevel[i][j] = currentLevel[i][j].flipTile(currentLevel[i][j].getX(), currentLevel[i][j].getY(), currentLevel[i][j].getFace());
    }
}
