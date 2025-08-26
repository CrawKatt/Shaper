package net.shaper;

import dev.architectury.registry.ReloadListenerRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.shaper.api.ShapesReader;

public class ShaperMod {
    public static final String MOD_ID = "shaper";

    public static void init() {
        ReloadListenerRegistry.register(PackType.SERVER_DATA, new ShapesReader());
    }
}
