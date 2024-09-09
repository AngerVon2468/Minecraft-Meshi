package wiiu.mavity.minecraft_meshi.item.itemtype;

import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

public class DragonHeartItem extends Item {

    public DragonHeartItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand hand) {
        super.use(world, player, hand);
        if (!world.isClientSide()) {
            player.sendSystemMessage(Component.literal("What the fuck why did you eat that"));
            player.hurt(player.damageSources().magic(), 7.0f);
        }
        return super.use(world, player, hand);
    }
}