package com.yonatanwn.presidentialvoting;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Candidate {

    private OfflinePlayer player;
    private List<OfflinePlayer> votes;

    public Candidate(OfflinePlayer player) {
        this(player, new ArrayList<>());
    }

    public Candidate(OfflinePlayer player, List<OfflinePlayer> votes) {
        this.votes = votes;
        this.player = player;
    }
    public void vote(Player player){
        votes.add(player);
    }

    public String toString(){
       return "The player " + player.getName() + " has " + votes.size() + " vote(s).";
    }

    public String getCandidateName(){
        return player.getName();
    }

    public boolean hasVoteOfPlayer(Player player) {
        return votes.contains(player);
    }

    public String getUUID() {
       return player.getUniqueId().toString();
    }

    public List<OfflinePlayer> getVotes() {
        return votes;
    }
}
