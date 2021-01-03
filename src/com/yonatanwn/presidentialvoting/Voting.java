package com.yonatanwn.presidentialvoting;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Voting implements CommandExecutor {
    private Vote vote;
    private Race race;
    public Voting(Vote vote, Race race){
        this.vote = vote;
        this.race = race;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(isValidVote(commandSender, args)){
            processVote(commandSender,args);
            return true;
        }
        return true;
    }

    private void processVote(CommandSender commandSender, String[] args) {
        PoliticalRace politicalRace = race.getRaceList().get(args[0].toLowerCase());
        Player voter = (Player) commandSender;

        politicalRace.vote(voter, args[1]);

    }

    private boolean isValidVote(CommandSender commandSender, String[] args) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED+ "Only active players may vote!");
            return false;
        }else if(args.length != 2){
            commandSender.sendMessage(ChatColor.RED+ "Invalid amount of arguments.");
            return false;
        }else if(!race.getRaceList().containsKey(args[0].toLowerCase())){
            commandSender.sendMessage(ChatColor.RED+ "The race " + args[0] + " does not exist! Fuck off!");
            return false;
        }else if(!race.getRaceList().get(args[0].toLowerCase()).containsPlayer(args[1])){
            commandSender.sendMessage(ChatColor.RED+ "The candidate " + args[1] + " is not running in the race " + args[0] + ". Fuck off!");
            return false;
        }else if(race.getRaceList().get(args[0].toLowerCase()).hasPlayerVote((Player) commandSender)){
            commandSender.sendMessage(ChatColor.RED+ "You can't vote more than once now fuck off!");
            return false;
        }
        return true;
    }




}
