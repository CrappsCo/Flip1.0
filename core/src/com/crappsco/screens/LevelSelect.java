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
    final Flip game;
    public static final float WIDTH = 1080;
    public static final float HEIGHT = 1920;
    public static final float tileSpacing = 145;

    private float runTime = 0;

    public Vector3 touchPoint;
    public Viewport viewport;
    public OrthographicCamera camera;
    public static Tile[][] currentGrid;
    public static Tile[][] currentLevel;
    public static int rowNum, colNum, moves;



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

    // LEVEL CREATION FUNCTIONS

    public static Tile[][] createGrid(int[][] faces){
        Tile[][] level = new Tile[rowNum][colNum];
        int [][] face = faces;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                level[i][j] = new Tile(tileSpacing * i  + (45f + WIDTH - (tileSpacing * colNum))/2, tileSpacing * j + (HEIGHT - 100f - (tileSpacing * rowNum)), face[i][j], false);
            }
        }
        return level;
    }


    public static Tile[][] createLevel(int[][] faces){
        Tile[][] level = new Tile[rowNum][colNum];
        int [][] face = faces;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                level[i][j] = new Tile(tileSpacing * i + (45f + WIDTH - (tileSpacing * colNum))/2, tileSpacing * j + tileSpacing, face[i][j], true);
            }
        }
        return level;
    }

    //LEVEL FUNCTIONS

    public void Level1() {
        rowNum = 5;
        colNum = 5;
        moves = 5;
        int[][] winningGrid = new int[][]{
                { 1, 1, 1, 1, 1},  // this is column 1 NOT row 1
                { 1, 0, 0, 0, 1},
                { 0, 1, 0, 1, 0},
                { 1, 0, 0, 0, 1},
                { 1, 1, 1, 1, 1}
        };
        int[][] playerGrid = new int[][]{
                { 0, 0, 0, 0, 0},  // this is column 1 NOT row 1
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0}
        };
        currentGrid = createGrid(winningGrid);
        currentLevel = createLevel(playerGrid);
    }

    public void Level2() {
        rowNum = 4;
        colNum = 4;
        moves = 5;
        int[][] winningGrid = new int[][]{
                { 1, 0, 0, 1},  // this is column 1 NOT row 1
                { 0, 0, 0, 0},
                { 0, 0, 0, 0},
                { 1, 0, 0, 1}
        };
        int[][] playerGrid = new int[][]{
                { 0, 0, 0, 0},  // this is column 1 NOT row 1
                { 0, 0, 0, 0},
                { 0, 0, 0, 0},
                { 0, 0, 0, 0}
        };
        currentGrid = createGrid(winningGrid);
        currentLevel = createLevel(playerGrid);
    }

    @Override
    public void render(float delta) {
        runTime += delta;

        //****************************************UPDATE SECTION****************************************
        //Set current level
        if (Gdx.input.justTouched()) {
            touchPoint = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            Level2();
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
    public void show() {

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
