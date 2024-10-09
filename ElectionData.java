package edu.wpi.hw6;

import java.util.*;

public class ElectionData {

    public HashMap <String, Votes> candidateVotes;
    public I3VoteStrategy strategy;
    public ElectionData(I3VoteStrategy strategy) {
        this.candidateVotes = new HashMap <>();
        this.strategy = strategy;


    }
    public HashMap<String, Votes> getCandidateVotes(){
        HashMap<String, Votes> cv = new HashMap <>();
        for(Map.Entry<String, Votes> candidate: candidateVotes.entrySet()){
            cv.put(candidate.getKey(),new Votes(candidate.getValue()));
        }
        return cv;

    }

    public void setStrategy(I3VoteStrategy strategy){
        this.strategy = strategy;
        this.candidateVotes = getCandidateVotes();
    }

    public Set<String> getCandidates(){
        return getCandidateVotes().keySet();
    }

    public void nominateCandidate(String person) throws AlreadyNominatedException{
        if(candidateVotes.containsKey(person)){
            throw new AlreadyNominatedException(person);}
        candidateVotes.put(person, new Votes(0,0,0));

    }
    public void submitVote(String first, String second, String third) throws CandidateNotNominatedException, MoreThanOnceException{
        if(! candidateVotes.containsKey(first)){
            throw new CandidateNotNominatedException(first);
        }
        if(! candidateVotes.containsKey(second)){
            throw new CandidateNotNominatedException(second);
        }
        if(! candidateVotes.containsKey(third)){
            throw new CandidateNotNominatedException(third);
        }
        if(first.equals(second)){
            throw new MoreThanOnceException(second);
        }
        if(first.equals(third)){
            throw new MoreThanOnceException(third);
        }
        if(second.equals(third)){
            throw new MoreThanOnceException(third);
        }
        candidateVotes.put(first, new Votes(candidateVotes.get(first).getFirstVotes()+ 1,candidateVotes.get(first).getSecondVotes(),candidateVotes.get(first).getThirdVotes()));
        candidateVotes.put(second, new Votes(candidateVotes.get(second).getFirstVotes(),candidateVotes.get(second).getSecondVotes()+1,candidateVotes.get(second).getThirdVotes()));
        candidateVotes.put(third, new Votes(candidateVotes.get(third).getFirstVotes(),candidateVotes.get(third).getSecondVotes(),candidateVotes.get(third).getThirdVotes()+1));



    }
    public Optional<String> calculateWinner(){

        return this.strategy.calculateWinner(getCandidateVotes());
    }

}