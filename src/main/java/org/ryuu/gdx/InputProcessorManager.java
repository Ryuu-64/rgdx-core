package org.ryuu.gdx;

import com.badlogic.gdx.InputMultiplexer;
import lombok.Getter;
import lombok.Setter;

public class InputProcessorManager {
    @Getter
    @Setter
    public static InputMultiplexer inputMultiplexer;

    private InputProcessorManager() {
    }
}