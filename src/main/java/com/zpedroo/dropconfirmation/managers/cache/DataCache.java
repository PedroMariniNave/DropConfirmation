package com.zpedroo.dropconfirmation.managers.cache;

import com.zpedroo.dropconfirmation.objects.ConfirmationItem;
import com.zpedroo.dropconfirmation.utils.FileUtils;
import com.zpedroo.dropconfirmation.utils.color.Colorize;
import lombok.Getter;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DataCache {

    private final List<ConfirmationItem> confirmationItems = getItemsFromConfig();

    private List<ConfirmationItem> getItemsFromConfig() {
        List<ConfirmationItem> ret = new ArrayList<>(4);
        FileUtils.Files file = FileUtils.Files.CONFIG;
        for (String str : FileUtils.get().getSection(file, "Items")) {
            String type = FileUtils.get().getString(file, "Items." + str + ".type", null);
            String name = Colorize.getColored(FileUtils.get().getString(file, "Items." + str + ".name", null));
            List<String> lore = Colorize.getColored(FileUtils.get().getStringList(file, "Items." + str + ".lore"));
            List<String> nbtList = FileUtils.get().getStringList(file, "Items." + str + ".nbt");
            List<Enchantment> enchantments = new ArrayList<>(2);
            for (String enchantmentName : FileUtils.get().getStringList(file, "Items." + str + ".enchants")) {
                enchantments.add(Enchantment.getByName(enchantmentName));
            }

            ret.add(new ConfirmationItem(type, name, lore, nbtList, enchantments));
        }

        return ret;
    }
}