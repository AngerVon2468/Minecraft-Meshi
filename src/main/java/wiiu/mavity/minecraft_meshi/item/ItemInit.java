package wiiu.mavity.minecraft_meshi.item;

import net.minecraft.world.effect.*;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import wiiu.mavity.minecraft_meshi.MinecraftMeshi;
import wiiu.mavity.minecraft_meshi.item.itemtype.DragonHeartItem;

import java.lang.reflect.Field;
import java.util.*;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MinecraftMeshi.MOD_ID);

    public static final List<RegistryObject<Item>> MOD_ITEMS = new ArrayList<>();

    public static final RegistryObject<Item> MONSTER_GUTS = ITEMS.register("monster_guts",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(2).saturationMod(3.7f).effect(
                    () -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.85f).meat().build()).stacksTo(64)));

    public static final RegistryObject<Item> MONSTER_HEART = ITEMS.register("monster_heart",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(0).saturationMod(0.7f).effect(
                    () -> new MobEffectInstance(MobEffects.HUNGER, 1000, 3), 2.5f).meat().build()).stacksTo(64)));

    public static final RegistryObject<Item> DRAGON_HEART = ITEMS.register("dragon_heart",
            () -> new DragonHeartItem(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(-3).saturationMod(0.7f).effect(
                    () -> new MobEffectInstance(MobEffects.HUNGER, 2000, 9), 2.9f).meat().build()).stacksTo(64)));

    public static final RegistryObject<Item> TREASURE_INSECT = ITEMS.register("treasure_insect",
            () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().alwaysEat().nutrition(4).saturationMod(0.3f).meat().build())));

    public static void addItemsToList() {
        for (Field field : ItemInit.class.getFields()) {
            if (field.getType().equals(RegistryObject.class)) {
                field.setAccessible(true);
                try {
                    MOD_ITEMS.add((RegistryObject<Item>) field.get(ItemInit.class));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        addItemsToList();
    }
}