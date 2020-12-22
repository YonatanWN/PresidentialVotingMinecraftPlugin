package com.yonatanwn.presidentialvoting;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;


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
        if(args.length != 2 || !isPositveInteger(args[1])) {
            return false;
        }
        return true;
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



        String racename = args[0];
        int raceMaxCandidates = Integer.parseInt(args[1]);






    }
    public HashMap<String, PoliticalRace> getRaceList(){
        return raceList;
    }


}
