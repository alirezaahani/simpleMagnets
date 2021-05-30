package alirezaahani.simpleMagnets.items;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.tag.Tag;

public class magnet extends Item {

    private final static short maxRange = 16;
    private final static String stateId = "ACTIVE";
    private int ticks = 0;

    private final static Tag<Item> notTeleportableItem = TagRegistry.item(new Identifier("simple_magnets","not_teleportable_item"));

    private void initState(ItemStack stack) {
        if(!stack.isEmpty()) {
            if(!stack.hasTag()) {
                stack.setTag(new NbtCompound());
            }
            NbtCompound nbt = stack.getTag();
            if(!nbt.contains(stateId)) {
                nbt.putBoolean(stateId, false);
            }
        }
    }

    private boolean getState(ItemStack stack) {
        if(!stack.isEmpty()) {
            this.initState(stack);
            return stack.getTag().getBoolean(stateId);
        }
        return false;
    }

    private void setState(ItemStack stack, boolean state) {
        this.initState(stack);
        stack.getTag().putBoolean(stateId, state);
    }

    private void switchState(ItemStack stack) {
        this.setState(stack, !getState(stack));
    }

    public magnet() {
        super(new FabricItemSettings().group(ItemGroup.TOOLS).maxDamage(1024).rarity(Rarity.UNCOMMON));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {    
        ItemStack stack = playerEntity.getStackInHand(hand);
        this.switchState(stack);

        return TypedActionResult.success(stack);
    }
    
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!(entity instanceof PlayerEntity))
            return;
        
        this.ticks++;
        PlayerEntity player = (PlayerEntity) entity;
        boolean hasCollected = false;

        if(!(ticks % 5 == 0))
            return;
        
        if(!this.getState(stack))
            return;
        
        if(player.isSneaking())
            return;
        
        for(Entity nearbyEntity: world.getEntitiesByType(EntityType.ITEM, player.getBoundingBox().expand(maxRange), EntityPredicates.VALID_ENTITY))
        {
            ItemEntity itemEntity = (ItemEntity) nearbyEntity;
            if(!(itemEntity.getStack().isIn(notTeleportableItem)))
            {
                nearbyEntity.onPlayerCollision(player);
                hasCollected = true;
            }
        }
        
        if ((!player.isCreative()) && hasCollected)
        {
            stack.damage(1, player, (p) -> { p.sendToolBreakStatus(player.getActiveHand()); });
        }
        
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.simple_magnets.magnet.tooltip.range", maxRange));
        tooltip.add(new TranslatableText("item.simple_magnets.magnet.tooltip.state", this.getState(itemStack) ? "Enabled" : "Disabled"));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean hasGlint(ItemStack itemStack) {
        return this.getState(itemStack);
    }
    
}
