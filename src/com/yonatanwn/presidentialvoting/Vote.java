package com.yonatanwn.presidentialvoting;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Vote extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[PresidentialVoting] has been enabled");
        this.getCommand("race").setExecutor(new Race());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[PresidentialVoting] has been disabled");
    }

}
// Nominate A candidate
// Candidate = Player on the server and vote count
// Multiple Job races
// vote on a existing candidate
// Winner gets Presidential Perms

