package alirezaahani.simpleMagnets;

import alirezaahani.simpleMagnets.items.magnet;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class simpleMagnets implements ModInitializer {

	public static final magnet MAGNET_INSTANCE = new magnet();
	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("simple_magnets","magnet"), MAGNET_INSTANCE);
	} 
}
