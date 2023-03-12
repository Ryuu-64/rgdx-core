package org.ryuu.gdx.scenes.scene2d;

import org.ryuu.gdx.ApplicationListenerManagement;

import java.util.ArrayList;
import java.util.List;

public class SceneManagement {
    private static final List<Scene> scenes = new ArrayList<>();

    private SceneManagement() {
    }

    public static void loadScene(Scene scene) {
        scenes.add(scene);
        CanvasManagement.getGameplayCanvas().getStage().addActor(scene.getRoot());
        for (Canvas canvas : scene.getCanvases()) {
            canvas.attachTo(ApplicationListenerManagement.getApplicationListener());
        }
    }

    public static void unloadScene(Scene scene) {
        boolean isRemove = scenes.remove(scene);
        if (isRemove) {
            scene.dispose();
        }
    }
}
