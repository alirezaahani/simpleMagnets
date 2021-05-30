package alirezaahani.simpleMagnets;

import alirezaahani.simpleMagnets.items.magnet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class simpleMagnets implements ModInitializer {

	public static final magnet MAGNET_INSTANCE = new magnet((short)16, 1024);
	public static final magnet STRONG_MAGNET_INSTANCE = new magnet((short)32, 1500);
	public static final String MOD_ID = "simple_magnets";
	public static final Tag<Item> magnets = TagRegistry.item(new Identifier("simple_magnets","magnets"));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"magnet"), MAGNET_INSTANCE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"strong_magnet"), STRONG_MAGNET_INSTANCE);
	} 
}
