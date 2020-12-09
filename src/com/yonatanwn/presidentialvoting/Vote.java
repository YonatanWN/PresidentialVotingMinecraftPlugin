package com.yonatanwn.presidentialvoting;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Vote extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[PresidentialVoting] has been enabled");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[PresidentialVoting] has been disabled");
    }
}
