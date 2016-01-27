package com.crappsco.FrameworkHelpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Ray on 2016-01-20.
 */
public class AssetLoader {

    public static Texture bg;
    public static Texture redcircle, bluecircle;

    public static Animation flipAnimation;

    public static void load() {


        bg = new Texture("background.png");
        redcircle = new Texture("redcircle.png");
        bluecircle = new Texture("bluecircle.png");


    }

    public static void dispose() {

        bg.dispose();
        redcircle.dispose();
        bluecircle.dispose();
    }


}
