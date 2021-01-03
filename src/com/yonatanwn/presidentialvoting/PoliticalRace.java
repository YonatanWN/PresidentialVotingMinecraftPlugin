package com.yonatanwn.presidentialvoting;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;

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

    public void removeCandidate(String playerName){
        Iterator iterator = candidates.iterator();
        while(iterator.hasNext()){
            Candidate candidate = (Candidate) iterator.next();
            if(candidate.getCandidateName().equalsIgnoreCase(playerName)){
                iterator.remove();
            }
        }
    }

    public boolean containsPlayer(String playerName){
        for(Candidate candidate : candidates){
            if(candidate.getCandidateName().equalsIgnoreCase(playerName)){
                return true;
            }
        }
        return false;
    }

    public int getMaxAmountOfCandidates(){
        return maxAmountOfCandidates;
    }
    public ArrayList<Candidate> getCandidates(){
        return candidates;
    }

}
