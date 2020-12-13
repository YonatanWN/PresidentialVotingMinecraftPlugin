package com.yonatanwn.presidentialvoting;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;

import java.util.HashMap;


public class Race implements CommandExecutor {
    private HashMap<String, PoliticalRace> raceList = new HashMap<>();
    private CommandSender commandSender;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        this.commandSender = commandSender;
        if(args.length == 0){
            commandSender.sendMessage("Invalid Amount of arguments. Use the create, add, remove, or close subcommands.");
            return false;
        }else{
            parseFirstSubCommand(args);
        }
        return true;
    }

    private void parseFirstSubCommand(String[] args) {
        switch(args[1]){
            case "create":
                processCreateRace(args);
            case "close":
                break;
            case "add":
                break;
            case "remove":
                break;
        }
    }

    private void processCreateRace(String[] args) {
        try{
            int maxCandidates = Integer.parseInt(args[3]);
            String raceName = args[2];
            if(raceList.containsKey(raceName)){
                commandSender.sendMessage(ChatColor.RED + "A race with the name "+ raceName + " already exists.");
            }else {
                raceList.put(raceName, new PoliticalRace(maxCandidates));
            }

        }catch (NumberFormatException ex){
            commandSender.sendMessage(ChatColor.RED + "The maximum amount of candidates must be an Integer.");
        }
    }


}
