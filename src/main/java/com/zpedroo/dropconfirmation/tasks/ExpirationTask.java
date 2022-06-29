package com.zpedroo.dropconfirmation.tasks;

import com.zpedroo.dropconfirmation.DropConfirmation;
import com.zpedroo.dropconfirmation.enums.Status;
import com.zpedroo.dropconfirmation.objects.Confirmation;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import static com.zpedroo.dropconfirmation.utils.config.Settings.EXPIRE_AFTER;

public class ExpirationTask extends BukkitRunnable {

    private final Plugin plugin = DropConfirmation.get();
    private final Confirmation confirmation;

    public ExpirationTask(@NotNull Confirmation confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public void run() {
        if (confirmation.getStatus() != Status.PENDING) return;

        confirmation.setStatus(Status.INVALID);
    }

    public void start() {
        this.runTaskLaterAsynchronously(plugin, EXPIRE_AFTER * 20L);
    }
}