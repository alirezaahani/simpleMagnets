package pythoner.alirezaahani;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class liteMagnets implements ModInitializer {

	public static final MagnetItem MAGNET = new MagnetItem(new FabricItemSettings().group(ItemGroup.TOOLS).maxDamage(256),128);

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("lite_magnets", "magnet"), MAGNET);
	} 
}
