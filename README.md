Voting Machine Program
This program simulates a ranked voting system for a small committee. Users can nominate candidates, cast ranked votes, and change the voting strategy at runtime. The system supports two voting strategies: MostFirstVotesStrategy and MostAgreeableStrategy.

To run the program, run the command:
java VotingMachine


Features
ElectionData: Manages candidate nominations, vote submission, and winner calculation using the current voting strategy.
Voting Strategies: Implements MostFirstVotesStrategy and MostAgreeableStrategy to calculate the winner.
Exceptions: Handles edge cases with custom exceptions such as AlreadyNominatedException, CandidateNotNominatedException, and MoreThanOnceException.
Interactive Command-Line Interface: Allows users to nominate candidates, vote, and switch strategies in real-time.
