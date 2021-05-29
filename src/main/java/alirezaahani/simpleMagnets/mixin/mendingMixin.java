package alirezaahani.simpleMagnets.mixin;

import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;

import alirezaahani.simpleMagnets.simpleMagnets;

@Mixin(MendingEnchantment.class)
public class mendingMixin {
    public boolean isAcceptableItem(ItemStack stack) {
        if(stack.getItem() == simpleMagnets.MAGNET_INSTANCE.asItem()) {
            return false;
        }

        return true;
     }
}
