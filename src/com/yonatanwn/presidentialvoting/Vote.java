package com.yonatanwn.presidentialvoting;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;


public class Vote extends JavaPlugin {
    Race race = new Race(this);
    Voting voting = new Voting(this, race);
    @Override
    public void onEnable() {
        this.getCommand("race").setExecutor(race);
        this.getCommand("vote").setExecutor(voting);
        this.saveDefaultConfig();

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[PresidentialVoting] has been enabled");

    }


    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[PresidentialVoting] has been disabled");

    }



}



