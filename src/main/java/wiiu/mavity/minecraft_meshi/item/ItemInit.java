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
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(2).saturationMod(0.3f).effect(
                    () -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.85f).meat().build())));

    public static final RegistryObject<Item> MONSTER_HEART = ITEMS.register("monster_heart",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(-1).saturationMod(0.1f).effect(
                    () -> new MobEffectInstance(MobEffects.HUNGER, 1000, 3), 2.5f).meat().build())));

    public static final RegistryObject<Item> DRAGON_HEART = ITEMS.register("dragon_heart",
            () -> new DragonHeartItem(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(-3).saturationMod(0.2f).effect(
                    () -> new MobEffectInstance(MobEffects.HUNGER, 2000, 9), 2.9f).meat().build())));

    public static final RegistryObject<Item> TREASURE_INSECT = ITEMS.register("treasure_insect",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(3).saturationMod(0.3f).meat().build())));

    public static final RegistryObject<Item> MONSTER_MEAT_STEW = ITEMS.register("monster_meat_stew",
            () -> new Item(new Item.Properties().durability(0).food(new FoodProperties.Builder().alwaysEat().effect(
                    () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0), 0.4f).nutrition(7).saturationMod(1f).meat().build())));

    public static final RegistryObject<Item> FRIED_TREASURE_INSECT = ITEMS.register("fried_treasure_insect",
            () -> new Item(new Item.Properties().durability(0).food(new FoodProperties.Builder().alwaysEat().effect(
                    () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 250, 0), 0.45f).nutrition(6).saturationMod(0.7f).meat().build())));

    public static final RegistryObject<Item> MONSTER_VEGGIE_STEW = ITEMS.register("monster_veggie_stew",
            () -> new Item(new Item.Properties().durability(0).food(new FoodProperties.Builder().alwaysEat().effect(
                    () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 350, 2), 0.6f).nutrition(8).saturationMod(1f).meat().build())));

    public static void addItemsToList() {
        for (Field field : ItemInit.class.getFields()) {
            if (field.getType().equals(RegistryObject.class)) {
                try {
                    MOD_ITEMS.add((RegistryObject<Item>) field.get(ItemInit.class));
                } catch (IllegalAccessException illegalAccessException) {
                    throw new RuntimeException(illegalAccessException);
                }
            }
        }
    }

    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        addItemsToList();
    }
}