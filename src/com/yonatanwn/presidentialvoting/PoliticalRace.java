package com.yonatanwn.presidentialvoting;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PoliticalRace {

    private int maxAmountOfCandidates;
    private ArrayList<Candidate> candidates;

    public PoliticalRace(int maxAmountOfCandidates){
        this.maxAmountOfCandidates = maxAmountOfCandidates;
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



    public int getMaxAmountOfCandidates(){
        return maxAmountOfCandidates;
    }
    public ArrayList<Candidate> getCandidates(){
        return candidates;
    }

}
