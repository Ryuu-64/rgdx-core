package org.ryuu.gdx.scenes.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ryuu.gdx.ApplicationListenerManager;
import org.ryuu.gdx.MulticastApplicationListener;
import org.ryuu.rgdx.RGdxApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MulticastApplicationListenerTest {
    private RGdxApplication rgdxApplication;

    private MulticastApplicationListener applicationListener;

    @BeforeEach
    void createApplicationListener() {
        rgdxApplication = new RGdxApplication();
        applicationListener = ApplicationListenerManager.getApplicationListener();
    }

    @AfterEach
    void launch() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1920, 1080);
        new Lwjgl3Application(rgdxApplication, config);
    }

    @Test
    void scene() {
        applicationListener.create.add(() -> {
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
    }
}
