package com.yonatanwn.presidentialvoting;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Locale;


public class Race implements CommandExecutor {
    private HashMap<String, PoliticalRace> raceList = new HashMap<>();
    private CommandSender commandSender;
    private Vote vote;
    public Race(Vote vote){
        this.vote = vote;
    }

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
        switch(args[0]){
            case "create":
                if(validateCreateRace(args)){
                    processCreateRace(args);
                }
            case "close":
                break;
            case "add":
                break;
            case "remove":
                break;
            default:
                commandSender.sendMessage("There was an issue parsing the command. Please try again in the form /race <subcommand> <args>");
        }
    }

    private boolean validateCreateRace(String[] args) {
        if(args.length != 2){
            commandSender.sendMessage(ChatColor.RED + "There is an invalid amount of arguments.");
            return false;
        }else if(!isPositveInteger(args[1])){
            commandSender.sendMessage(ChatColor.RED + "The maximum amount of allowed candidates must be a valid integer");
            return false;
        }else if(raceList.containsKey(args[0].toLowerCase())){
            commandSender.sendMessage(ChatColor.RED + "There is already a list by that name");
            return false;
        }else{
            return true;
        }
    }

    private boolean isPositveInteger(String commandInt) {
        try{
            int integer = Integer.parseInt(commandInt);
            if(integer >= 0){
                return true;
            }else {
                return false;
            }
        }catch (NumberFormatException e){
            return false;
        }


    }

    private void processCreateRace(String[] args) {
        String racename = args[0].toLowerCase();
        int raceMaxCandidates = Integer.parseInt(args[1]);
        raceList.put(racename,new PoliticalRace(raceMaxCandidates));

    }



    public HashMap<String, PoliticalRace> getRaceList(){
        return raceList;
    }


}
