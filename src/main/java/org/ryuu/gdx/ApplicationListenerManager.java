package org.ryuu.gdx;

import lombok.Getter;
import lombok.Setter;

public class ApplicationListenerManager {
    @SuppressWarnings("GDXJavaStaticResource")
    @Getter
    @Setter
    private static MulticastApplicationListener applicationListener;

    private ApplicationListenerManager() {
    }
}