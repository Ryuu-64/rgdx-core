package org.ryuu.gdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import org.ryuu.gdx.scenes.scene2d.Canvas;
import org.ryuu.gdx.scenes.scene2d.CanvasManager;

public class ApplicationListenerManager {
    @SuppressWarnings("GDXJavaStaticResource")
    private static MulticastApplicationListener applicationListener;

    private ApplicationListenerManager() {
    }

    public static MulticastApplicationListener getApplicationListener() {
        if (ApplicationListenerManager.applicationListener == null) {
            throw new IllegalStateException(
                    "Attempting to access program listener before program initialization or program listener is not created.\n" +
                    " If the program listener is not created, please create it using ApplicationListenerManager.create(float minWorldWidth, float minWorldHeight)."
            );
        }

        return ApplicationListenerManager.applicationListener;
    }

    /**
     * Since {@link Canvas} will create {@link Stage}, {@link SpriteBatch} will be created when Stage is created.
     * Some fields in the {@link Gdx} class (such as {@link Gdx#gl30} and {@link Gdx#graphics}) are used when creating a {@link SpriteBatch}.
     * Therefore, the creation of {@link Canvas} must be at or after {@link ApplicationListener#create()}.
     */
    public static synchronized void create(float minWorldWidth, float minWorldHeight) {
        if (minWorldWidth < 0 || minWorldHeight < 0) {
            throw new IllegalArgumentException("World width and height must be non-negative.");
        }

        if (ApplicationListenerManager.applicationListener != null) {
            throw new IllegalStateException("Program listener has already been created and cannot be created again.");
        }

        InputProcessorManager.setInputMultiplexer(new InputMultiplexer());
        MulticastApplicationListener applicationListener = new MulticastApplicationListener();
        applicationListener.render.add(() -> Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT));
        applicationListener.create.add(() -> {
            Canvas gameplayCanvas = new Canvas(new ExtendViewport(minWorldWidth, minWorldHeight, new OrthographicCamera()));
            gameplayCanvas.attachTo(applicationListener);
            CanvasManager.setGameplayCanvas(gameplayCanvas);
        });
        ApplicationListenerManager.applicationListener = applicationListener;
    }
}