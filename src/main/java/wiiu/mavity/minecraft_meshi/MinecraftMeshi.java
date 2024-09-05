package wiiu.mavity.minecraft_meshi;

import net.minecraft.world.item.*;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import org.slf4j.*;

import wiiu.mavity.minecraft_meshi.block.BlockInit;
import wiiu.mavity.minecraft_meshi.item.ItemInit;
import wiiu.mavity.minecraft_meshi.tag.TagInit;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MinecraftMeshi.MOD_ID)
public class MinecraftMeshi {

    public static final String MOD_ID = "minecraft_meshi";

    private static final Logger LOGGER = LoggerFactory.getLogger("Minecraft Meshi");

    public MinecraftMeshi() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.init(modEventBus);
        BlockInit.init(modEventBus);
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