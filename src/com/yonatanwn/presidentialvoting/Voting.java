package com.yonatanwn.presidentialvoting;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Voting implements CommandExecutor {
    private Vote vote;
    private Race race;
    public Voting(Vote vote, Race race){
        this.vote = vote;
        this.race = race;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}
