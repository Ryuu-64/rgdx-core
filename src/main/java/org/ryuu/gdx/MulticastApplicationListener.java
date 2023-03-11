package org.ryuu.gdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.utils.Disposable;
import org.ryuu.functional.Actions;
import org.ryuu.functional.Actions2Args;

public class MulticastApplicationListener implements ApplicationListener, Disposable {
    public final Actions create = new Actions();

    public final Actions2Args<Integer, Integer> resize = new Actions2Args<>();

    public final Actions render = new Actions();

    public final Actions pause = new Actions();

    public final Actions resume = new Actions();

    public final Actions dispose = new Actions();

    @Override
    public void create() {
        create.invoke();
    }

    @Override
    public void resize(int width, int height) {
        resize.invoke(width, height);
    }

    @Override
    public void render() {
        render.invoke();
    }

    @Override
    public void pause() {
        pause.invoke();
    }

    @Override
    public void resume() {
        resume.invoke();
    }

    @Override
    public void dispose() {
        dispose.invoke();
    }
}