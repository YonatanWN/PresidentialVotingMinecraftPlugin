package com.yonatanwn.presidentialvoting;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PoliticalRace {

    private int maxAmountofCandidates;
    private String positionName;
    private ArrayList candidates;

    public PoliticalRace(String positionName, int maxAmountOfCandidates){
        this.positionName = positionName;
        this.maxAmountofCandidates = maxAmountOfCandidates;
        this.candidates = new ArrayList<Player>();
    }
    public void addCandidate(Player player){
        candidates.add(player);
    }

    public void removeCandidate(Player player){
        if(candidates.contains(player)){
            candidates.remove(player);
        }
    }


}
