package com.yonatanwn.presidentialvoting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
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
            return true;
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
                if(isvalidRemove(args)){
                    processRemove(args);
                }
                break;
            case "info":
                if(isvalidInfo(args)){
                    processInfo(args);
                }
                break;
            default:
                commandSender.sendMessage("There was an issue parsing the command. Please try again in the form /race <create|close|add|remove|list> <args>");
        }
    }

    private void processInfo(String[] args) {
        commandSender.sendMessage("The current voting results of the race:  " + args[1]);
        for(Candidate candidate : raceList.get(args[1].toLowerCase()).getCandidates()){
            commandSender.sendMessage(candidate.toString());
        }
    }

    private boolean isvalidInfo(String[] args) {
        if(args.length != 2){
            return false;
        }
        else if(raceList.containsKey(args[1].toLowerCase())){
            return true;
        }

        return false;
    }

    private boolean isvalidRemove(String[] args) {
        if(!raceList.containsKey(args[2].toLowerCase())){
            commandSender.sendMessage(ChatColor.RED + "The race " + args[2] + " does not exist.");
            return false;
        }else if(!raceList.get(args[2]).containsPlayer(args[1])){
            commandSender.sendMessage(ChatColor.RED + "The player " + args[1] + " is not running in the political race: " + args[2]);
            return false;
        }

            return true;
    }
    private void processRemove(String[] args){
        raceList.get(args[2].toLowerCase()).removeCandidate(args[1]);
        commandSender.sendMessage(ChatColor.GREEN + "The candidate "+ args[1] + " has been removed from the race: " + args[2]);
    }

    private void processList() {
        if(raceList.isEmpty()){
            commandSender.sendMessage("There are currently no active races");
        }else{
            for(String key: raceList.keySet()){
                commandSender.sendMessage(ChatColor.GREEN + key + ": " + raceList.get(key).getMaxAmountOfCandidates()+ " candidate maximum" );
                for(Candidate candidate : raceList.get(key).getCandidates()){
                    commandSender.sendMessage(candidate.toString());
                }

            }
        }

    }

    private void processAdd(String[] args) {
        raceList.get(args[2].toLowerCase()).addCandidate(new Candidate(Bukkit.getOfflinePlayer(args[1])));
    }

    private boolean isvalidAdd(String[] args) {
        if(args.length != 3){
            commandSender.sendMessage(ChatColor.RED + "There is an invalid amount of arguments for an Add subcommand");
            return false;
        }else if(Bukkit.getOfflinePlayer(args[1]) == null){
            commandSender.sendMessage(ChatColor.RED + "The player " + args[1] + " could not be found");
            return false;
        }else if(!raceList.containsKey(args[2].toLowerCase())){
            commandSender.sendMessage(ChatColor.RED + "The race " + args[2] + "does not exist");
            return false;
        }else if(raceList.get(args[2].toLowerCase()).getCandidates().size() >= raceList.get(args[2].toLowerCase()).getMaxAmountOfCandidates()){
            commandSender.sendMessage(ChatColor.RED + "The race " +  args[2] + " is full");
            return false;
        }else{
            return true;
        }

    }

    private void processCloseRace(String[] args) {
        commandSender.sendMessage("The race " + args[1] + " has been closed");
        if(!raceList.get(args[1]).getCandidates().isEmpty()){
            commandSender.sendMessage("The final results of " + args[1]);
            for(Candidate candidate : raceList.get(args[1]).getCandidates()){
                commandSender.sendMessage(candidate.toString());
            }
        }
        raceList.remove(args[1].toLowerCase());
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


    private void processCreateRace(String[] args) {
        String racename = args[1].toLowerCase();
        int raceMaxCandidates = Integer.parseInt(args[2]);
        raceList.put(racename,new PoliticalRace(raceMaxCandidates));
        commandSender.sendMessage(ChatColor.GREEN + "The race " + racename + " has been added");
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
    public HashMap<String, PoliticalRace> getRaceList(){
        return raceList;
    }


    public void addPoliticalRace(String s, int parseInt) {
        raceList.put(s, new PoliticalRace(parseInt));
    }

    public void addCandidate(String racename, Candidate candidate) {
        raceList.get(racename).addCandidate(candidate);
    }

    public List<Candidate> getCandidates(String raceName) {
        return raceList.get(raceName).getCandidates();
    }

}
