package org.ryuu.gdx.scenes.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Scene implements Disposable {
    @Getter
    private final Group root = new Group();

    @Getter
    private final List<Canvas> canvases = new ArrayList<>();

    public Scene() {
    }

    public void addActor(Actor actor) {
        root.addActor(actor);
    }

    public void addCanvas(Canvas canvas) {
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