package com.yonatanwn.presidentialvoting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                if(isvalidCreateRace(args)){
                    processCreateRace(args);
                }
                break;
            case "close":
                if(isvalidCloseRace(args)){
                    processCloseRace(args);
                }
                break;
            case "add":
                if(isvalidAdd(args)){
                    processAdd(args);
                }
                break;
            case "list":
                processList();
                break;
            case "remove":
                break;
            default:
                commandSender.sendMessage("There was an issue parsing the command. Please try again in the form /race <subcommand> <args>");
        }
    }

    private void processList() {
        for(String key: raceList.keySet()){
            commandSender.sendMessage(key + ": " + raceList.get(key).getMaxAmountOfCandidates()+ " candidate maximum" );
        }
    }

    private void processAdd(String[] args) {
        commandSender.sendMessage("This add command wil be processed");
    }

    private boolean isvalidAdd(String[] args) {
        if(args.length != 2){
            return false;
        }else{
            if(Bukkit.getPlayer(args[2]) != null && raceList.get(args[1]).getCandidates().size() < raceList.get(args[1]).getMaxAmountOfCandidates() ){
                return true;
            }else{
                return false;
            }

        }


    }

    private void processCloseRace(String[] args) {
        for(String key : raceList.keySet()){
            if(args[1] == key){
                PoliticalRace politicalRace = raceList.get(args[1]);
                for(Candidate candidate: politicalRace.getCandidates()) {
                    commandSender.sendMessage(ChatColor.GREEN + candidate.toString());
                }
            }
        }
    }

    private boolean isvalidCreateRace(String[] args) {
        if(args.length != 3){
            commandSender.sendMessage(ChatColor.RED + "There is an invalid amount of arguments.");
            return false;
        }else if(!isPositiveInteger(args[2])){
            commandSender.sendMessage(ChatColor.RED + "The maximum amount of allowed candidates must be a valid integer");
            return false;
        }else if(raceList.containsKey(args[1].toLowerCase())){
            commandSender.sendMessage(ChatColor.RED + "There is already a race by that name");
            return false;
        }else{
            return true;
        }
    }
    private boolean isvalidCloseRace(String[] args){
        if(args.length != 2){
            commandSender.sendMessage(ChatColor.RED + "There is an invalid amount of arguments.");
            return false;
        }else{
            if(raceList.containsKey(args[1])){
                return true;
            }else{
                commandSender.sendMessage(ChatColor.RED + "A race does not exist by that name.");
                return false;
            }
        }


    }

    private boolean isPositiveInteger(String commandInt) {
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
        String racename = args[1].toLowerCase();
        int raceMaxCandidates = Integer.parseInt(args[2]);
        raceList.put(racename,new PoliticalRace(raceMaxCandidates));
        commandSender.sendMessage("The race " + racename + " has been added");
    }



    public HashMap<String, PoliticalRace> getRaceList(){
        return raceList;
    }


}
