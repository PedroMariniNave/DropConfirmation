package com.zpedroo.dropconfirmation;

import com.zpedroo.dropconfirmation.listeners.DropListener;
import com.zpedroo.dropconfirmation.managers.DataManager;
import com.zpedroo.dropconfirmation.utils.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class DropConfirmation extends JavaPlugin {

    private static DropConfirmation instance;
    public static DropConfirmation get() { return instance; }

    public void onEnable() {
        instance = this;
        new FileUtils(this);
        new DataManager();

        registerListeners();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new DropListener(), this);
    }
}