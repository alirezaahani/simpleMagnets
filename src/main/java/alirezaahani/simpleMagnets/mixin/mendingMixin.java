package alirezaahani.simpleMagnets.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import alirezaahani.simpleMagnets.*;

@Mixin(Enchantment.class)
public class mendingMixin {
    @Inject(at = @At("RETURN"), method = "isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z", cancellable = true)
    public void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
        if((Object)this instanceof MendingEnchantment && stack.isIn(simpleMagnets.magnets)) {
            info.setReturnValue(false);
        }
    }
}
