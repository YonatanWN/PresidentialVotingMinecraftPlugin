package com.yonatanwn.presidentialvoting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class Vote extends JavaPlugin {
    Race race = new Race(this);
    Voting voting = new Voting(this, race);
    @Override
    public void onEnable() {
        this.getCommand("race").setExecutor(race);
        this.getCommand("race").setTabCompleter(race);
        this.getCommand("vote").setExecutor(voting);
        this.getCommand("vote").setTabCompleter(voting);
        loadRacesAndVotes();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[PresidentialVoting] has been enabled");

        Bukkit.getScheduler().runTaskTimer(this, () -> saveRacesAndVotes(), 20*60*25 ,20*60*25);
    }


    @Override
    public void onDisable() {
        saveRacesAndVotes();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[PresidentialVoting] has been disabled");

    }
    private void loadRacesAndVotes(){
        if(getConfig().contains("races")){
            getConfig().getConfigurationSection("races").getKeys(false).forEach(raceName-> {
                int raceMaxCandidates = getConfig().getInt("races." + raceName+ ".maxAmountOfCandidates");
                race.addPoliticalRace(raceName,raceMaxCandidates);
                if(getConfig().contains("races." + raceName + ".candidates")){
                    getConfig().getConfigurationSection("races." + raceName + ".candidates").getKeys(false).forEach(candidateUUIDString -> {
                        List<OfflinePlayer> offlinePlayers = getConfig().getStringList("races." + raceName + ".candidates.votes").stream().map(uuid -> Bukkit.getOfflinePlayer(UUID.fromString(uuid))).collect(Collectors.toList());
                        Candidate candidate = new Candidate(Bukkit.getOfflinePlayer(UUID.fromString(candidateUUIDString)), offlinePlayers);
                        race.addCandidate(raceName,candidate);
                    });
                }


            });
        }

    }
    private void saveRacesAndVotes(){
       for(String raceName : race.getRaceList().keySet()){
           getConfig().set("races." + raceName+ ".maxAmountOfCandidates",race.getRaceList().get(raceName).getMaxAmountOfCandidates());
           for (Candidate candidate : race.getCandidates(raceName)) {
               getConfig().set("races."+raceName+".candidates."+ candidate.getUUID(), candidate.getVotes().stream().map(offlinePlayer -> offlinePlayer.getUniqueId().toString()).collect(Collectors.toList()));
           }

       }

        saveConfig();
    }


}



