package net.shaper.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public final class ShapesReader extends SimplePreparableReloadListener<Void> {
    public ShapesReader() {}

    @Override
    protected Void prepare(ResourceManager rm, ProfilerFiller pf) { return null; }

    @Override
    protected void apply(Void prep, ResourceManager rm, ProfilerFiller pf) {
        ShaperAPI.clear();

        Map<ResourceLocation, Resource> files = rm.listResources("", rl -> rl.getPath().endsWith(".json"));

        for (Map.Entry<ResourceLocation, Resource> e : files.entrySet()) {
            ResourceLocation fileLoc = e.getKey();
            if (!fileLoc.getNamespace().equals(ShaperAPI.NAMESPACE)) continue;

            try (BufferedReader r = new BufferedReader(
                    new InputStreamReader(e.getValue().open(), StandardCharsets.UTF_8))) {

                JsonElement root = JsonParser.parseReader(r);
                if (!root.isJsonArray()) continue;

                VoxelShape shape = parseBoxes(root.getAsJsonArray());

                String path = fileLoc.getPath();
                String name = path.substring(0, path.length() - 5);

                ResourceLocation publicId = new ResourceLocation(ShaperAPI.NAMESPACE, name);
                ShaperAPI.put(publicId, shape);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static VoxelShape parseBoxes(JsonArray arr) {
        VoxelShape out = Shapes.empty();
        for (JsonElement el : arr) {
            if (!el.isJsonArray()) continue;
            JsonArray a = el.getAsJsonArray();
            if (a.size() != 6) continue;

            double x1=a.get(0).getAsDouble(), y1=a.get(1).getAsDouble(), z1=a.get(2).getAsDouble();
            double x2=a.get(3).getAsDouble(), y2=a.get(4).getAsDouble(), z2=a.get(5).getAsDouble();
            if (x1 > x2 || y1 > y2 || z1 > z2) continue;

            out = Shapes.or(out, Shapes.box(x1, y1, z1, x2, y2, z2));
        }
        return out;
    }
}