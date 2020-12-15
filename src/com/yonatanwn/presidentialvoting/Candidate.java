package com.yonatanwn.presidentialvoting;

import org.bukkit.entity.Player;

public class Candidate {

    private Player player;
    private int votes;

    public Candidate(Player player){
        this.player = player;
        this.votes = 0;
    }
    public void vote(){
        votes++;
    }

    public String toString(){
       return "The player " + player.getName() + " has " + votes + " vote(s).";
    }


}
