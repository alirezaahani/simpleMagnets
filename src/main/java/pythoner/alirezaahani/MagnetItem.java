package pythoner.alirezaahani;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class MagnetItem extends Item {

    private int maxRange;

    public MagnetItem(Settings settings, int maxRange) {
        super(settings);
        this.maxRange = maxRange;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {    
        
        ItemStack stack = playerEntity.getStackInHand(hand);

        for(Entity e: world.getEntitiesByType(EntityType.ITEM, new Box(playerEntity.capeX-this.maxRange,playerEntity.capeY-this.maxRange,playerEntity.capeZ-this.maxRange ,playerEntity.capeX+this.maxRange,playerEntity.capeY+this.maxRange,playerEntity.capeZ+this.maxRange), EntityPredicates.VALID_ENTITY))
        {
            e.teleport(playerEntity.capeX, playerEntity.capeY, playerEntity.capeZ);
        }
        
        playerEntity.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE, 1.0F, 1.0F);
        
        if (!playerEntity.isCreative())
        {
            stack.damage(1, (LivingEntity)playerEntity, (Consumer)((p) -> {
                ((LivingEntity) p).sendToolBreakStatus(playerEntity.getActiveHand());
             }));
        }

        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.lite_magnets.magnet.tooltip", this.maxRange));
    }
}
