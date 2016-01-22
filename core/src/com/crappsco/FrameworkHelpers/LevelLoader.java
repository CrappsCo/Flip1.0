package com.crappsco.FrameworkHelpers;

import com.crappsco.GameObjects.Tile;

import java.util.logging.Level;

/**
 * Created by Ray on 2016-01-21.
 */
public class LevelLoader {

    public static int rowNum, colNum;

    public static Tile[][] level1;

    public static void load() {
        level1 = Level1();
    }

    public static Tile[][] Level1() {
        rowNum = 5;
        colNum = 5;
        Tile[][] level1 = new Tile[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                // face = (int) (Math.random() * 2);
                level1[i][j] = new Tile(120f * i + 240f, 120f * j + 100f, 0);
            }
        }
        return level1;
    }

    public static void dispose() {
        level1 = null;
    }


}
