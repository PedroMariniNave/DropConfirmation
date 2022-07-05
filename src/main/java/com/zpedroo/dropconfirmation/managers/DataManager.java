package com.zpedroo.dropconfirmation.managers;

import com.zpedroo.dropconfirmation.managers.cache.DataCache;
import com.zpedroo.dropconfirmation.objects.ConfirmationItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class DataManager {

    private static DataManager instance;
    public static DataManager getInstance() { return instance; }

    private final DataCache dataCache = new DataCache();

    public DataManager() {
        instance = this;
    }

    public boolean isConfirmationItem(@NotNull ItemStack item) {
        return getConfirmationItem(item) != null;
    }

    public ConfirmationItem getConfirmationItem(@NotNull ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        for (ConfirmationItem confirmationItem : dataCache.getConfirmationItems()) {
            if (!confirmationItem.checkType(item.getType().toString())) continue;
            if (!confirmationItem.checkEnchantments(meta.getEnchants().keySet())) continue;
            if (!confirmationItem.checkName(meta.getDisplayName())) continue;
            if (!confirmationItem.checkLore(meta.getLore())) continue;

            return confirmationItem;
        }

        return null;
    }

    public DataCache getCache() {
        return dataCache;
    }
}