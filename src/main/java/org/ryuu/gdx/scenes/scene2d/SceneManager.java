package org.ryuu.gdx.scenes.scene2d;

import lombok.Getter;
import org.ryuu.gdx.ApplicationListenerManager;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    @Getter
    private static final List<Scene> scenes = new ArrayList<>();

    private SceneManager() {
    }

    public static void loadScene(Scene scene) {
        scenes.add(scene);
        CanvasManager.getGameplayCanvas().getStage().addActor(scene.getRoot());
        for (Canvas canvas : scene.getCanvases()) {
            canvas.attachTo(ApplicationListenerManager.getApplicationListener());
        }
    }

    public static void unloadScene(Scene scene) {
        boolean isRemove = scenes.remove(scene);
        if (isRemove) {
            scene.dispose();
        }
    }
}
