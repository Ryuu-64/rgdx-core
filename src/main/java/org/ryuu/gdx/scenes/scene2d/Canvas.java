package org.ryuu.gdx.scenes.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import lombok.Getter;
import org.ryuu.functional.Action;
import org.ryuu.functional.Action2Args;
import org.ryuu.functional.Actions;
import org.ryuu.gdx.InputProcessorManager;
import org.ryuu.gdx.MulticastApplicationListener;

public class Canvas implements Disposable {
    @Getter
    protected final Stage stage;

    public final Actions afterDraw = new Actions();

    public final Actions dispose = new Actions();

    public Canvas(Viewport viewport) {
        stage = new Stage(viewport);
        InputProcessorManager.getInputMultiplexer().addProcessor(stage);
    }

    public void render() {
        stage.act();
        stage.draw();
        afterDraw.invoke();
    }

    public void resize(int screenWidth, int screenHeight) {
        stage.getViewport().update(screenWidth, screenHeight);
    }

    public void addActor(Actor actor) {
        stage.addActor(actor);
    }

    @Override
    public void dispose() {
        stage.dispose();
        dispose.invoke();
    }

    public void attachTo(MulticastApplicationListener applicationListener) {
        Action render = this::render;
        Action2Args<Integer, Integer> resize = this::resize;
        applicationListener.render.add(render);
        applicationListener.resize.add(resize);
        dispose.add(() -> {
            applicationListener.render.remove(render);
            applicationListener.resize.remove(resize);
        });
    }
}