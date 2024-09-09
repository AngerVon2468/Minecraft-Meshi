package wiiu.mavity.minecraft_meshi;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.minecraft_meshi.item.ItemInit;
import wiiu.mavity.minecraft_meshi.tag.TagInit;

import java.util.Random;

@Mod(MinecraftMeshi.MOD_ID)
public class MinecraftMeshi {

    public static final String MOD_ID = "minecraft_meshi";

    public MinecraftMeshi() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.init(modEventBus);
        TagInit.init();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::creativeModeTabSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    private void creativeModeTabSetup(@NotNull BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            for (RegistryObject<Item> item : ItemInit.MOD_ITEMS) {
                event.accept(item);
            }
        }
    }

    @SubscribeEvent
    public void onMobDeath(@NotNull LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Random random = new Random();
        Entity entity1 = event.getSource().getEntity() != null ? event.getSource().getEntity() : entity;
        if (entity1 instanceof Player) {
            if (entity instanceof Monster) {
                if (random.nextBoolean()) {
                    if (random.nextBoolean() && random.nextBoolean()) {
                        entity.spawnAtLocation(ItemInit.MONSTER_GUTS.get(), 4);
                    } else if (random.nextBoolean()) {
                        entity.spawnAtLocation(ItemInit.MONSTER_GUTS.get(), 3);
                    } else if (random.nextBoolean() && random.nextBoolean()) {
                        entity.spawnAtLocation(ItemInit.MONSTER_GUTS.get(), 6);
                    }
                }
                if (random.nextBoolean() && random.nextBoolean() && random.nextBoolean()) {
                    entity.spawnAtLocation(ItemInit.MONSTER_HEART.get(), 2);
                }
            }
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {}
    }
}