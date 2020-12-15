package com.yonatanwn.presidentialvoting;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PoliticalRace {

    private int maxAmountofCandidates;
    private String positionName;
    private ArrayList candidates;

    public PoliticalRace(int maxAmountOfCandidates){
        this.maxAmountofCandidates = maxAmountOfCandidates;
        this.candidates = new ArrayList<Candidate>();
    }
    public void addCandidate(Candidate candidate){
        candidates.add(candidate);
    }

    public void removeCandidate(Candidate candidate){
        if(candidates.contains(candidate)){
            candidates.remove(candidate);
        }
    }


}
