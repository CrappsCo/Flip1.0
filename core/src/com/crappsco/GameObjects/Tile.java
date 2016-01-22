package com.crappsco.GameObjects;

import com.badlogic.gdx.math.Circle;


/**
 * Created by C on 2016-01-16.
 */
public class Tile {
    public static final float radius = 50;
    public float x, y;
    public int face;
    public Circle circle;

    public Tile(float xloc, float yloc, int type) {
        x = xloc;
        y = yloc;
        face = type;
        circle = new Circle(x + radius, y + radius, radius);
    }


    public boolean contains(float xLoc, float yLoc) {
        if (circle.contains(xLoc, yLoc)) {
            return true;
        } else {
            return false;
        }
    }

    public Tile flipTile(float xLoc, float yLoc, float face) {
        Tile flippedTile;
        if (face == 0) {
            flippedTile = new Tile(xLoc, yLoc, 1);
        } else {
            flippedTile = new Tile(xLoc, yLoc, 0);
        }
        return flippedTile;
    }

    public int getFace() {
        return face;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}