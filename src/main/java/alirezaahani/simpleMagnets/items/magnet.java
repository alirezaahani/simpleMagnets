package alirezaahani.simpleMagnets.items;

import java.util.List;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.tag.Tag;

public class magnet extends Item {

    private int maxRange;
    public static Tag<Item> notTeleportableItem = TagRegistry.item(new Identifier("simple_magnets","not_teleportable_item"));

    public magnet(Settings settings, int maxRange) {
        super(settings);
        this.maxRange = maxRange;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {    
        
        ItemStack stack = playerEntity.getStackInHand(hand);
        for(Entity e: world.getEntitiesByType(EntityType.ITEM, 
            new Box(
                playerEntity.getX()-this.maxRange,
                playerEntity.getY()-(this.maxRange / 2),
                playerEntity.getZ()-this.maxRange,
                playerEntity.getX()+this.maxRange,
                playerEntity.getY()+(this.maxRange / 2),
                playerEntity.getZ()+this.maxRange), 
            EntityPredicates.VALID_ENTITY))
        {
            ItemEntity itemEntity = (ItemEntity) e;
            if(!(itemEntity.getStack().getItem().isIn(notTeleportableItem)))
            {
                e.teleport(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());
            }
        }
        
        if (!playerEntity.isCreative())
        {
            stack.damage(1, playerEntity, (p) -> { p.sendToolBreakStatus(playerEntity.getActiveHand()); });
        }
        
        playerEntity.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE, 1.0F, 1.0F);
        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.simple_magnets.magnet.tooltip", this.maxRange));
    }
    
}
