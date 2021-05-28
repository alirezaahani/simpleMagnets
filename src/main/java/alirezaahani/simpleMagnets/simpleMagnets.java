package alirezaahani.simpleMagnets;

import alirezaahani.simpleMagnets.items.magnet;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class simpleMagnets implements ModInitializer {

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("simple_magnets","magnet"), new magnet());
	} 
}
