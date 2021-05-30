package alirezaahani.simpleMagnets;

import alirezaahani.simpleMagnets.items.magnet;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class simpleMagnets implements ModInitializer {

	public static final magnet MAGNET_INSTANCE = new magnet();
	public static final String MOD_ID = "simple_magnets";

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"magnet"), MAGNET_INSTANCE);
	} 
}
