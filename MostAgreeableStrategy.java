package edu.wpi.hw6;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MostAgreeableStrategy implements I3VoteStrategy {
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes){

    int mostVotes = 0;
    String candidateWMV = " ";
        for(Map.Entry<String, Votes> candidate: votes.entrySet()){
            if(candidate.getValue().getFirstVotes() > mostVotes ){
                candidateWMV = candidate.getKey();
                mostVotes = candidate.getValue().getFirstVotes();
            }
            if(candidate.getValue().getSecondVotes() > mostVotes ){
                candidateWMV = candidate.getKey();
                mostVotes = candidate.getValue().getSecondVotes();
            }
            if(candidate.getValue().getThirdVotes() > mostVotes ){
                candidateWMV = candidate.getKey();
                mostVotes = candidate.getValue().getThirdVotes();
            }
    }
        return Optional.of(candidateWMV);
    }
}
