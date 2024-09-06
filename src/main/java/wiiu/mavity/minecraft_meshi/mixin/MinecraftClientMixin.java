package wiiu.mavity.minecraft_meshi.mixin;

import net.minecraft.client.Minecraft;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {

    @Inject(method = "createTitle", at = @At("RETURN"), cancellable = true)
    public void createTitle(@NotNull CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("!Minecraft 1.20.1");
    }
}