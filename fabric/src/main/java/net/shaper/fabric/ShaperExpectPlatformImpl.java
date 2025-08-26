package net.shaper.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class ShaperExpectPlatformImpl {
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
