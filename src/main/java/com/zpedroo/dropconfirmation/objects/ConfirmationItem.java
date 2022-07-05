package com.zpedroo.dropconfirmation.objects;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

@Data
public class ConfirmationItem {

    private final String type;
    private final String name;
    private final List<String> lore;
    private final List<Enchantment> enchantments;

    public boolean checkType(@NotNull String typeToCheck) {
        if (type == null) return true;

        return typeToCheck.contains(type);
    }

    public boolean checkName(@Nullable String nameToCheck) {
        if (name == null) return true;
        if (nameToCheck == null) return false;

        return StringUtils.equals(name, nameToCheck);
    }

    public boolean checkLore(@Nullable List<String> loreToCheck) {
        if (lore == null) return true;
        if (loreToCheck == null) return false;

        return lore.containsAll(loreToCheck);
    }

    public boolean checkEnchantments(@NotNull Set<Enchantment> enchantmentsToCheck) {
        if (enchantments.isEmpty()) return true;
        if (enchantmentsToCheck.isEmpty()) return false;

        return enchantments.containsAll(enchantmentsToCheck);
    }
}