package net.shaper.forge;

import dev.architectury.platform.forge.EventBuses;
import net.shaper.ShaperMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ShaperMod.MOD_ID)
public class ShaperModForge {
    public ShaperModForge() {
        EventBuses.registerModEventBus(ShaperMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ShaperMod.init();
    }
}
