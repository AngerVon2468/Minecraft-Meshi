package wiiu.mavity.minecraft_meshi.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class Util {

    public static boolean condition() {
        String obfuscationMappingsMyBeloved = Minecraft.getInstance().getUser().getName();
        return obfuscationMappingsMyBeloved.equals("Dev") || obfuscationMappingsMyBeloved.equals("EpicVon") || obfuscationMappingsMyBeloved.equals("EpicVon2468");
    }

    public static boolean condition2(RegistryObject<Item> item) {
        return nullSafe(item) && !item.getId().getPath().equals("mashed_monster_food") && condition();
    }

    public static boolean nullSafe(Object o) {
        return o != null;
    }
}