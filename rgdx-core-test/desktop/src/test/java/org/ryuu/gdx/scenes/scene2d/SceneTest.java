package org.ryuu.gdx.scenes.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ryuu.gdx.ApplicationListenerManager;
import org.ryuu.gdx.MulticastApplicationListener;
import org.ryuu.rgdx.RGdxApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SceneTest {
    private RGdxApplication rgdxApplication;

    private MulticastApplicationListener applicationListener;

    @BeforeEach
    void createApplicationListener() {
        rgdxApplication = new RGdxApplication();
        applicationListener = ApplicationListenerManager.getApplicationListener();
    }

    @Test
    void scene() {
        applicationListener.create.add(() -> {
            Scene scene = new Scene() {{
                Image image = new Image(new Texture("badlogic.jpg"));
                addActor(image);
                image.setSize(1, 1);
                getRoot().addAction(Actions.delay(1, new Action() {
                    @Override
                    public boolean act(float delta) {
                        SceneManager.unloadScene(SceneManager.getScenes().get(0));
                        return true;
                    }
                }));
            }};
            SceneManager.loadScene(scene);
        });
    }

    @AfterEach
    void launch() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1920, 1080);
        new Lwjgl3Application(rgdxApplication, config);
    }
}