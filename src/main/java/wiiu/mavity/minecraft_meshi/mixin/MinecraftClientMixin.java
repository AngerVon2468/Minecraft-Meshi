package wiiu.mavity.minecraft_meshi.mixin;

import net.minecraft.client.*;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin {

    @Shadow public abstract User getUser();

    @Inject(method = "createTitle", at = @At("RETURN"), cancellable = true)
    public void createTitle(@NotNull CallbackInfoReturnable<String> cir) {
        String original = cir.getReturnValue();
        String name = this.getUser().getName();
        String title = name.equals("EpicVon") || name.equals("EpicVon2468") || name.equals("Dev") ? original.replace("Minecraft Forge*", "Minecraft") : original;
        cir.setReturnValue(title);
    }
}