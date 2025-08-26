package net.shaper.quilt;

import net.shaper.ShaperMod;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class ShaperModQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        ShaperMod.init();
    }
}
