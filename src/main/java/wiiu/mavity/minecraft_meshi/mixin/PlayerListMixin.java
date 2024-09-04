package wiiu.mavity.minecraft_meshi.mixin;

import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public abstract class PlayerListMixin {

    @Shadow public abstract void broadcastSystemMessage(Component p_240618_, boolean p_240644_);

    @Inject(method = "placeNewPlayer", at = @At("TAIL"))
    public void placeNewPlayer(Connection connection, @NotNull ServerPlayer player, CallbackInfo ci) {
        if (player.getStringUUID().equals("1fa11ca4-c589-444d-97d4-3a7f4dfdbe0d")) {
            this.broadcastSystemMessage(Component.literal("Loading magical code that disables epic fight specifically for Kado!!!"), false);
            this.broadcastSystemMessage(Component.literal("(L bozo skill issue)"), false);
        }
    }
}