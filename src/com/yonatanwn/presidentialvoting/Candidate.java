package com.yonatanwn.presidentialvoting;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Candidate {

    private Player player;
    private ArrayList<Player> votes;

    public Candidate(Player player){
        this.player = player;
        votes = new ArrayList<Player>();
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

}
