package org.ryuu.gdx.scenes.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
import org.ryuu.gdx.ApplicationListenerManagement;

import java.util.ArrayList;
import java.util.List;

public class Scene implements Disposable {
    private final Group root = new Group();

    private final List<Canvas> canvases = new ArrayList<>();

    public Scene() {
        CanvasManagement.getGameplayCanvas().getStage().addActor(root);
    }

    public void addActor(Actor actor) {
        root.addActor(actor);
    }

    public void addCanvas(Canvas canvas) {
        canvas.attachTo(ApplicationListenerManagement.getApplicationListener());
        canvases.add(canvas);
    }

    @Override
    public void dispose() {
        root.remove();
        for (Canvas canvas : canvases) {
            canvas.dispose();
        }
    }
}