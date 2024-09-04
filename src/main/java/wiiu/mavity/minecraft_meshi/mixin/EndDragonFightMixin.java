package wiiu.mavity.minecraft_meshi.mixin;

import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.dimension.end.EndDragonFight;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wiiu.mavity.minecraft_meshi.item.ItemInit;

@Mixin(EndDragonFight.class)
public class EndDragonFightMixin {

    @Inject(method = "setDragonKilled", at = @At("TAIL"))
    public void setDragonKilled(@NotNull EnderDragon dragon, CallbackInfo ci) {

        dragon.spawnAtLocation(ItemInit.DRAGON_HEART.get());
    }
}