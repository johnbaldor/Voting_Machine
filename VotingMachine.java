package edu.wpi.hw6;

import org.w3c.dom.ls.LSOutput;

import java.util.Optional;
import java.util.Scanner;

public class VotingMachine {


    ElectionData ed = new ElectionData(new MostFirstVotesStrategy());
    Scanner scanner = new Scanner(System.in);


    public VotingMachine() {
    }

    private void run(){

        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("current candidates are: " + ed.getCandidates());
            System.out.println("Do you want to [n]ominate someone, [v]ote for someone, change winner [s]trategy, see who [w]on, or [q]uit?");
            String text = scanner.nextLine();

            if (text.charAt(0) == 'q' || text.charAt(0) == 'Q') {
                keepGoing = false;}

            else if (text.charAt(0) == 'n' || text.charAt(0) == 'N') {
                nominateCandidate();}

            else if (text.charAt(0) == 'v' || text.charAt(0) == 'V') {
                System.out.println("please enter your first, second, and third choice (respectively, one at a time)");
                System.out.println("first vote: ");
                String vote1 = scanner.nextLine();
                System.out.println("second vote: ");
                String vote2 = scanner.nextLine();
                System.out.println("third vote: ");
                String vote3 = scanner.nextLine();

                try {ed.submitVote(vote1, vote2, vote3);}
                catch (CandidateNotNominatedException nn) {
                    System.out.println(nn.getMessage());
                    System.out.println("would like to nominate the candidate [y]es/[n]o");
                    String answer = scanner.nextLine();
                    if (answer.charAt(0) == 'y' || answer.charAt(0) == 'Y') {
                        nominateCandidate();
                    }}
                catch (MoreThanOnceException mo) {
                    System.out.println(mo.getMessage());
                }}

            else if (text.charAt(0) == 's' || text.charAt(0) == 'S') {
                System.out.println("Which strategy would you like to use? most [f]irst votes or most [a]greeable?");
                String strat = scanner.nextLine();
                if (strat.charAt(0) == 'f' || strat.charAt(0) == 'F'){
                    ed.setStrategy(new MostFirstVotesStrategy());
                }
                else if(strat.charAt(0) == 'a' || strat.charAt(0) == 'A'){
                    ed.setStrategy(new MostAgreeableStrategy());
                }
                else{
                    System.out.println("Bad input");
                }

            }
            else if (text.charAt(0) == 'w' || text.charAt(0) == 'W') {
                Optional<String> winner =  ed.calculateWinner();
                if(winner.isPresent()){
                    System.out.println("the winner is: " + ed.calculateWinner().toString().replace("Optional[","").replace("]",""));
                }
                else{System.out.println("No clear winner under the current strategy.");}
            }


        }

    }
    private void nominateCandidate(){
        System.out.println("Enter the name of the candidate you want to nominate");
        String nominate = scanner.nextLine();
        try {
            ed.nominateCandidate(nominate);
        } catch (AlreadyNominatedException an) {
            System.out.println(an.getMessage());
        }
    }


    public static void main(String[] args) {
        VotingMachine vt = new VotingMachine();
        vt.run();
    }






}


