package alirezaahani.simpleMagnets.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import alirezaahani.simpleMagnets.simpleMagnets;

@Mixin(Enchantment.class)
public class mendingMixin {
    @Inject(at = @At("RETURN"), method = "isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z", cancellable = true)
    public boolean isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
        if((Enchantment)(Object)this instanceof MendingEnchantment && stack.getItem() == simpleMagnets.MAGNET_INSTANCE.asItem()) {
            info.setReturnValue(false);
        }
        return info.getReturnValue();
    }
}
