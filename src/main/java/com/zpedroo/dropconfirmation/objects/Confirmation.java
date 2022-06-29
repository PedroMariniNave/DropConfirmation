package com.zpedroo.dropconfirmation.objects;

import com.zpedroo.dropconfirmation.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

@Data
@AllArgsConstructor
public class Confirmation {

    private final ItemStack item;
    private Status status;

    public void confirm() {
        status = Status.CONFIRMED;
    }
}