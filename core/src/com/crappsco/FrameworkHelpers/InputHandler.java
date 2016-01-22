package com.crappsco.FrameworkHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.crappsco.GameObjects.Tile;
import com.crappsco.GameWorld.GameRenderer;

/**
 * Created by Ray on 2016-01-20.
 */
public class InputHandler implements InputProcessor {


    private OrthographicCamera cam;
    public static Vector3 touchPoint;

    private Tile[][] currentLevel;

    public InputHandler(GameRenderer renderer) {
        cam = renderer.camera;
        currentLevel = renderer.currentLevel;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPoint = cam.unproject(new Vector3(screenX, screenY, 0));
        //THIS SHOULD JUST BE ONE UPDATE CALL....
        for (int i = 0; i < LevelLoader.rowNum; i++) {
            for (int j = 0; j < LevelLoader.colNum; j++) {
                Tile tile = currentLevel[i][j];
                if (tile.contains(touchPoint.x, touchPoint.y)) {
                    flipCross(i, j);
                    //SET A BOOLEAN ANIMATE = TRUE; SO RENDERER KNOWS WHEN TO ANIMATE
                }
            }
        }
        return false;
    }

    //WE CAN PUT ALL OF OUR FLIP PATTERNS SOMEWHERE. NOT SURE WHERE SO I JUST PUT THIS HERE FOR NOW.!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public void flipCross(int i, int j) {
        if (i + 1 < LevelLoader.rowNum)
            currentLevel[i + 1][j] = currentLevel[i + 1][j].flipTile(currentLevel[i + 1][j].getX(), currentLevel[i + 1][j].getY(), currentLevel[i + 1][j].getFace());
        if (i - 1 >= 0)
            currentLevel[i - 1][j] = currentLevel[i - 1][j].flipTile(currentLevel[i - 1][j].getX(), currentLevel[i - 1][j].getY(), currentLevel[i - 1][j].getFace());
        if (j + 1 < LevelLoader.colNum)
            currentLevel[i][j + 1] = currentLevel[i][j + 1].flipTile(currentLevel[i][j + 1].getX(), currentLevel[i][j + 1].getY(), currentLevel[i][j + 1].getFace());
        if (j - 1 >= 0)
            currentLevel[i][j - 1] = currentLevel[i][j - 1].flipTile(currentLevel[i][j - 1].getX(), currentLevel[i][j - 1].getY(), currentLevel[i][j - 1].getFace());
        currentLevel[i][j] = currentLevel[i][j].flipTile(currentLevel[i][j].getX(), currentLevel[i][j].getY(), currentLevel[i][j].getFace());
    }

    //UNUSED INPUTS**************************************************************************************


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
