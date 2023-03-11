package org.ryuu.gdx;

import lombok.Getter;
import lombok.Setter;

public class ApplicationListenerManagement {
    @SuppressWarnings("GDXJavaStaticResource")
    @Getter
    @Setter
    private static MulticastApplicationListener applicationListener;

    private ApplicationListenerManagement() {
    }
}