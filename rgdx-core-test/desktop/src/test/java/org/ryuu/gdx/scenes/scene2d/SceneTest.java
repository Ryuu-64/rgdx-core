package org.ryuu.gdx.scenes.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ryuu.gdx.ApplicationListenerManager;
import org.ryuu.gdx.MulticastApplicationListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SceneTest {
    private MulticastApplicationListener applicationListener;

    @BeforeEach
    void createApplicationListener() {
        ApplicationListenerFactory.create(16, 9);
        applicationListener = ApplicationListenerManager.getApplicationListener();
    }

    @AfterEach
    void launch() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1920, 1080);
        new Lwjgl3Application(applicationListener, config);
    }

    @Test
    void scene() {
        applicationListener.create.add(() -> {
            Scene scene = new Scene() {{
                Image image = new Image(new Texture("badlogic.jpg"));
                addActor(image);
                image.setSize(1, 1);
                Gdx.app.postRunnable(() -> {
                    SceneManager.unloadScene(SceneManager.getScenes().get(0));
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
                });
            }};
            SceneManager.loadScene(scene);
        });
    }
}