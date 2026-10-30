package cis3723.okbu.edu;

public class Connect4TrieNode {
    // Array representing the 7 possible column moves (indexes 0 to 6)
    Connect4TrieNode[] children;
    
    // Optional: Can store evaluation scores, visit counts for MCTS, or a win flag
    boolean isEndGame; 
    double boardValue; 

    public Connect4TrieNode() {
        this.children = new Connect4TrieNode[7]; // Fixed 7 columns
        this.isEndGame = false;
        this.boardValue = 0.0;
    }
}
