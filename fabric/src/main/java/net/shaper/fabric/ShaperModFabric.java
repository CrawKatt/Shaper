package net.shaper.fabric;

import net.shaper.ShaperMod;
import net.fabricmc.api.ModInitializer;

public class ShaperModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ShaperMod.init();
    }
}
