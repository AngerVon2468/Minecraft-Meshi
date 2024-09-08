package wiiu.mavity.minecraft_meshi.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;

import org.jetbrains.annotations.NotNull;

public class TagInit {

    public static void init() {}

    public static final TagKey<Item> BUG_PARTS = registerTag("bug_parts");

    public static final TagKey<Item> MONSTER_MEAT = registerTag("monster_meat");

    public static final TagKey<Item> VEGGIES = registerTag("veggies");

    private static @NotNull TagKey<Item> registerTag(String name) {
        return ItemTags.create(new ResourceLocation("minecraft_meshi", name));
    }
}