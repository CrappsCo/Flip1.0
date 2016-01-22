package com.crappsco.GameWorld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crappsco.FrameworkHelpers.LevelLoader;
import com.crappsco.GameObjects.Tile;
import com.crappsco.FrameworkHelpers.AssetLoader;


/**
 * Created by Ray on 2016-01-20.
 */
public class GameRenderer {

    private GameUpdater myUpdater;

    public Viewport viewport;
    public OrthographicCamera camera;
    public final float WIDTH = 1080;
    public final float HEIGHT = 1920;

    public Tile[][] currentLevel;


    private SpriteBatch batcher;

    public GameRenderer(GameUpdater updater) {
        myUpdater = updater;
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        camera = new OrthographicCamera(25 * aspectRatio, 25);
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        viewport = new StretchViewport(WIDTH, HEIGHT, camera);
        viewport.apply();

        currentLevel = LevelLoader.level1;

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();

        //Drawing Background
        batcher.draw(AssetLoader.bg, 0, 0);

        //Drawing the tiles
        for (int i = 0; i < LevelLoader.rowNum; i++) {
            for (int j = 0; j < LevelLoader.colNum; j++) {
                Tile tile = currentLevel[i][j];
                if (tile.face == 0) {
                    batcher.draw(AssetLoader.bluecircle, tile.getX(), tile.getY());
                } else {
                    batcher.draw(AssetLoader.redcircle, tile.getX(), tile.getY());
                }
            }
        }

        //ASSETLOADER.FLIPANIMATION ...

        batcher.end();

    }


}
