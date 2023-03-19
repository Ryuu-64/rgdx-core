package org.ryuu.rgdx;

import com.badlogic.gdx.ApplicationListener;
import lombok.Getter;
import org.ryuu.gdx.ApplicationListenerManager;
import org.ryuu.gdx.MulticastApplicationListener;

public class RGdxApplication implements ApplicationListener {
    @Getter
    private final MulticastApplicationListener applicationListener;

    public RGdxApplication() {
        ApplicationListenerManager.create(16, 9);
        applicationListener = ApplicationListenerManager.getApplicationListener();
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