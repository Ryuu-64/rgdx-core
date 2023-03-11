package org.ryuu.gdx.scenes.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ryuu.gdx.ApplicationListenerManagement;
import org.ryuu.gdx.MulticastApplicationListener;
import org.ryuu.rgdx.RGdxApplication;

import static com.badlogic.gdx.utils.Align.left;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SceneTest {
    private RGdxApplication rgdxApplication;
    private MulticastApplicationListener applicationListener;

    @BeforeEach
    void createApplicationListener() {
        rgdxApplication = new RGdxApplication();
        applicationListener = ApplicationListenerManagement.getApplicationListener();
    }

    @Test
    void scene() {
        applicationListener.create.add(() -> {
            Scene scene = new Scene();
            Image imageInGameplay = new Image(new Texture("badlogic.jpg"));
            scene.addActor(imageInGameplay);
            imageInGameplay.setSize(1, 1);
            Canvas gameplayCanvas = CanvasManagement.getGameplayCanvas();
            imageInGameplay.setPosition(
                    gameplayCanvas.getViewport().getWorldWidth() / 2,
                    gameplayCanvas.getViewport().getWorldHeight() / 2,
                    left
            );

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    scene.dispose();
                    assertNotEquals(0, applicationListener.create.getUnicastList().size());
                    // gameplay canvas resize
                    assertEquals(1, applicationListener.resize.getUnicastList().size());
                    // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
                    // gameplay canvas render
                    assertEquals(2, applicationListener.render.getUnicastList().size());
                    assertEquals(0, applicationListener.pause.getUnicastList().size());
                    assertEquals(0, applicationListener.resume.getUnicastList().size());
                    assertEquals(0, applicationListener.dispose.getUnicastList().size());
                    Gdx.app.exit();
                }
            }, .1f);
        });
    }

    @AfterEach
    void launch() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1920, 1080);
        new Lwjgl3Application(rgdxApplication, config);
    }
}