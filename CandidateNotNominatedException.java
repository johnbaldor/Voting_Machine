package edu.wpi.hw6;

public class CandidateNotNominatedException extends Exception{
    private String candidateName;
    public CandidateNotNominatedException(String name){
        super(name + " was not nominated" );
        this.candidateName = name;

    }
    public String getCandidate(){
        return candidateName;
    }
}
