package com.crappsco.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crappsco.FrameworkHelpers.AssetLoader;
import com.crappsco.TweenAcessors.SpriteAccessor;
import com.crappsco.TweenAcessors.Value;
import com.crappsco.flip.Flip;


import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Ray on 2016-01-26.
 */
public class MainMenu implements Screen {
    public Viewport viewport;
    public OrthographicCamera camera;
    public final float WIDTH = 1080;
    public final float HEIGHT = 1920;

    private Sprite playButton, settings;
    private Circle playCircle, settingsCircle;

    private float runTime = 0;

    public Vector3 touchPoint;

    final Flip game;

    private TweenManager tweenManager;

    public MainMenu(Flip game) {

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
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        tweenManager = new TweenManager();

        spriteSetup();
        tweenSetup();
    }

    public void spriteSetup() {
        //Setting up playbutton
        playButton = new Sprite(AssetLoader.play);
        playButton.setColor(1, 1, 1, 0);
        playButton.setPosition((WIDTH / 2) - (playButton.getWidth() / 2), (HEIGHT / 2)
                - (playButton.getHeight() / 2));
        playCircle = new Circle((WIDTH / 2), (HEIGHT / 2), 141);
        //Setting up settings
        settings = new Sprite(AssetLoader.settings);
        settings.setColor(1, 1, 1, 0);
        settings.setPosition((WIDTH / 2) - (playButton.getWidth() / 2), (HEIGHT / 2) - playButton.getHeight()*2);
        settingsCircle = new Circle((WIDTH / 2), (HEIGHT / 2) - 282, 141);

    }

    public void tweenSetup() {

        Tween.setCombinedAttributesLimit(5);
//Spinning fade animation
        Timeline.createSequence()
                .delay(.75f)
                .beginParallel()
                .push(Tween.to(playButton, SpriteAccessor.ALPHA, 1.5f).target(1).ease(TweenEquations.easeInQuad))
                .push(Tween.to(playButton, SpriteAccessor.ROTATION, 1.5f).target(360).ease(TweenEquations.easeInQuad))
                .end()
                .start(tweenManager);


        //Spinning fade animation
        Timeline.createSequence()
                .delay(.75f)
                .beginParallel()
                .push(Tween.to(settings, SpriteAccessor.ALPHA, 1.5f).target(1).ease(TweenEquations.easeInQuad))
                .push(Tween.to(settings, SpriteAccessor.ROTATION, 1.5f).target(360).ease(TweenEquations.easeInQuad))
                .end()
                .start(tweenManager);

    }

    public void tweenTouch() {
        //Diagonal Flip animation

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                game.setScreen(new LevelSelect(game));
                dispose();
            }
        };
        Timeline.createSequence()
                .beginParallel()
                .push(Tween.to(playButton, SpriteAccessor.ROTATION, 0.75f).target(540))
                .push(Tween.to(playButton, SpriteAccessor.SCALEXY, 0.75f).target(1, 0))
                .end()
                .beginParallel()
                .push(Tween.to(playButton, SpriteAccessor.ROTATION, 0.75f).target(720))
                .push(Tween.to(playButton, SpriteAccessor.SCALEXY, 0.75f).target(1, 1))
                .end()
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(tweenManager);

        Timeline.createSequence()
                .beginParallel()
                .push(Tween.to(settings, SpriteAccessor.ROTATION, 0.75f).target(540))
                .push(Tween.to(settings, SpriteAccessor.SCALEXY, 0.75f).target(1, 0))
                .end()
                .beginParallel()
                .push(Tween.to(settings, SpriteAccessor.ROTATION, 0.75f).target(720))
                .push(Tween.to(settings, SpriteAccessor.SCALEXY, 0.75f).target(1, 1))
                .end()
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(tweenManager);
    }

    @Override
    public void show() {

    }

    public void update(float delta) {
        if (Gdx.input.justTouched()) {
            touchPoint = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (playCircle.contains(touchPoint.x, touchPoint.y)) {
                tweenTouch();
            }
        }
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        tweenManager.update(delta);
        update(delta);

        //****************************************RENDER SECTION****************************************
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batcher.begin();
        playButton.draw(game.batcher);
        settings.draw(game.batcher);
        game.font.draw(game.batcher, "F L I P", 0, 1880);
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

}
