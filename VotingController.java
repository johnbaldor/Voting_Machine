package edu.wpi.hw6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;

public class VotingController {


    public Label currentStrategy;
    @FXML
    private Label candidates;

    @FXML
    private TextField vote1;
    @FXML
    private TextField vote2;
    @FXML
    private TextField vote3;


    @FXML
    private Label winner;

    @FXML
    private TextField nominate;


    //TODO: ADD A FIELD FOR YOUR ElectionData OBJECT AND INITIALIZE IT WITH A MostFirstVotesStrategy
    ElectionData ed = new ElectionData(new MostFirstVotesStrategy());

    @FXML
    private Label error;

    @FXML
    protected void onVoteClick() {
        //TODO: USE this.vote1.getText()... etc, to pass data to your ElectionData field and cast a vote


        try {ed.submitVote(this.vote1.getText(), this.vote2.getText(), this.vote3.getText());
            this.error.setText("");
        }
        catch (CandidateNotNominatedException | MoreThanOnceException nn) {
            this.error.setText(nn.getMessage());
        }
    }
    @FXML
    protected void onNominateClick() {
        //TODO: Use this.nominate.getText() to pass data to your ElectionData field and nominate a candidate
        try {
            ed.nominateCandidate(this.nominate.getText());
            this.candidates.setText("Current candidates are: " + ed.getCandidates());
            this.error.setText("");
        } catch (AlreadyNominatedException an) {
            this.error.setText(an.getMessage());
        }
    }
    @FXML
    protected void onWinnerClick() {
        //TODO: Use winner.setText(...) to pass data from your ElectionData field to the GUI
        Optional<String> winner =  ed.calculateWinner();
        if(winner.isPresent()){
            this.winner.setText("the winner is: " + ed.calculateWinner().toString().replace("Optional[","").replace("]",""));
            this.error.setText("");
        }
        else{this.error.setText(" No clear winner under the current strategy.");}
    }

    @FXML
    protected void onStratClick() {

        if(ed.strategy instanceof MostFirstVotesStrategy){
            ed.setStrategy(new MostAgreeableStrategy());
            this.currentStrategy.setText("Current Strategy: Most Agreeable");

        }
        else{
            ed.setStrategy(new MostFirstVotesStrategy());
            this.currentStrategy.setText("Current Strategy: Most First Votes");
        }
    }


}

//TODO: Add methods for clicking the change strategy buttons

