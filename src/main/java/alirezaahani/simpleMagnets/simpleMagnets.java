package alirezaahani.simpleMagnets;

import alirezaahani.simpleMagnets.items.magnet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class simpleMagnets implements ModInitializer {

	public static final magnet MAGNET = new magnet(new FabricItemSettings().group(ItemGroup.TOOLS).maxDamage(128),64);
	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("simple_magnets","magnet"), MAGNET);
	} 
}
