package edu.wpi.hw6;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MostFirstVotesStrategy implements I3VoteStrategy{
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {

        int totalVotes = 0;
        int mostVotes = 0;
        String candidateWMV = " ";
        for(Map.Entry<String, Votes> candidate: votes.entrySet()){
            totalVotes = totalVotes + candidate.getValue().getFirstVotes();
            if(candidate.getValue().getFirstVotes() > mostVotes ){
                candidateWMV = candidate.getKey();
                mostVotes = candidate.getValue().getFirstVotes();
            }
        }
        if(totalVotes==0){
            return Optional.empty();
        }
        if (mostVotes*100 /totalVotes  > 50){

            return Optional.of(candidateWMV); }

        return Optional.empty();
    }
}
