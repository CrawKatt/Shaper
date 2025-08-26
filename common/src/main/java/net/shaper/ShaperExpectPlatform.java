package net.shaper;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class ShaperExpectPlatform {
    @ExpectPlatform
    public static Path getConfigDirectory() {
        throw new AssertionError();
    }
}
