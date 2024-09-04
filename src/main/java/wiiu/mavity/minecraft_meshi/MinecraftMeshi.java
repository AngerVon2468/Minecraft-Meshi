package wiiu.mavity.minecraft_meshi;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.*;

import wiiu.mavity.minecraft_meshi.block.BlockInit;
import wiiu.mavity.minecraft_meshi.item.ItemInit;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MinecraftMeshi.MOD_ID)
public class MinecraftMeshi {

    public static final String MOD_ID = "minecraft_meshi";

    private static final Logger LOGGER = LoggerFactory.getLogger("Minecraft Meshi");

    public MinecraftMeshi() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.init(modEventBus);
        BlockInit.init(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

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