package org.ryuu.gdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import org.ryuu.gdx.scenes.scene2d.Canvas;
import org.ryuu.gdx.scenes.scene2d.CanvasManagement;

public class ApplicationListenerFactory {
    /**
     * Since {@link Canvas} will create {@link Stage}, {@link SpriteBatch} will be created when Stage is created.
     * Some fields in the {@link Gdx} class (such as {@link Gdx#gl30} and {@link Gdx#graphics}) are used when creating a {@link SpriteBatch}.
     * Therefore, the creation of {@link Canvas} must be at or after {@link ApplicationListener#create()}.
     */
    public static void create(float minWorldWidth, float minWorldHeight) {
        if (ApplicationListenerManagement.getApplicationListener() != null) {
            return;
        }

        InputProcessorManagement.setInputMultiplexer(new InputMultiplexer());
        MulticastApplicationListener applicationListener = new MulticastApplicationListener();
        applicationListener.render.add(() -> Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT));
        applicationListener.create.add(() -> {
            Canvas gameplayCanvas = new Canvas(new ExtendViewport(minWorldWidth, minWorldHeight));
            gameplayCanvas.attachTo(applicationListener);
            CanvasManagement.setGameplayCanvas(gameplayCanvas);
        });
        ApplicationListenerManagement.setApplicationListener(applicationListener);
    }
}