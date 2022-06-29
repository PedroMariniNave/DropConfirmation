package com.zpedroo.dropconfirmation.listeners;

import com.zpedroo.dropconfirmation.enums.Status;
import com.zpedroo.dropconfirmation.managers.DataManager;
import com.zpedroo.dropconfirmation.objects.Confirmation;
import com.zpedroo.dropconfirmation.objects.SoundProperties;
import com.zpedroo.dropconfirmation.tasks.ExpirationTask;
import com.zpedroo.dropconfirmation.utils.config.Messages;
import com.zpedroo.dropconfirmation.utils.config.Sounds;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DropListener implements Listener {

    private final Map<Player, Confirmation> playersConfirmation = new HashMap<>();

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDrop(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (!DataManager.getInstance().isConfirmationItem(item)) return;

        Player player = event.getPlayer();
        Confirmation confirmation = playersConfirmation.remove(player);
        if (isConfirmed(confirmation)) return;
        if (isSameItem(confirmation, item) && !isInvalid(confirmation)) {
            confirmation.confirm();
            return;
        }

        event.setCancelled(true);

        for (String msg : Messages.DROP_AGAIN) {
            player.sendMessage(msg);
        }

        Confirmation newConfirmation = new Confirmation(item, Status.PENDING);
        ExpirationTask task = new ExpirationTask(newConfirmation);
        task.start();
        playersConfirmation.put(player, newConfirmation);

        SoundProperties soundProperties = Sounds.CONFIRMATION_SOUND;
        if (soundProperties != null && soundProperties.isEnabled()) {
            player.playSound(player.getLocation(), soundProperties.getSound(), soundProperties.getVolume(), soundProperties.getPitch());
        }
    }

    private boolean isConfirmed(@Nullable Confirmation confirmation) {
        return confirmation != null && confirmation.getStatus() == Status.CONFIRMED;
    }

    private boolean isInvalid(@Nullable Confirmation confirmation) {
        return confirmation != null && confirmation.getStatus() == Status.INVALID;
    }

    private boolean isSameItem(@Nullable Confirmation confirmation, @NotNull ItemStack item) {
        return confirmation != null && confirmation.getItem().isSimilar(item);
    }
}