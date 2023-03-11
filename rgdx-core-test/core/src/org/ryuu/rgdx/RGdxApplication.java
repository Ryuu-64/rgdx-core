package org.ryuu.rgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import lombok.Getter;
import org.ryuu.functional.Action;
import org.ryuu.gdx.ApplicationListenerFactory;
import org.ryuu.gdx.ApplicationListenerManagement;
import org.ryuu.gdx.MulticastApplicationListener;
import org.ryuu.gdx.physics.box2d.Box2dEngine;
import org.ryuu.gdx.scenes.scene2d.Canvas;
import org.ryuu.gdx.scenes.scene2d.CanvasManagement;

public class RGdxApplication implements ApplicationListener {
    @Getter
    private final MulticastApplicationListener applicationListener;

    public RGdxApplication() {
        ApplicationListenerFactory.create(16, 9);
        applicationListener = ApplicationListenerManagement.getApplicationListener();
    }

    @Override
    public void create() {
        applicationListener.create();
    }

    @Override
    public void resize(int width, int height) {
        applicationListener.resize(width, height);
    }

    @Override
    public void render() {
        applicationListener.render();
    }

    @Override
    public void pause() {
        applicationListener.pause();
    }

    @Override
    public void resume() {
        applicationListener.resume();
    }

    @Override
    public void dispose() {
        applicationListener.dispose();
    }
}