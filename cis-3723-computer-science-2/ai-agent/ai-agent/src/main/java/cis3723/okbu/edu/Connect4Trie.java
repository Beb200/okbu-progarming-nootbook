package cis3723.okbu.edu;

public class Connect4Trie {
    private final Connect4TrieNode root;

    public Connect4Trie() {
        this.root = new Connect4TrieNode();
    }

    /**
     * Inserts a sequence of moves (column history) into the Trie.
     * @param moveHistory An array of integers representing the sequence of column drops.
     */
    public void insert(int[] moveHistory) {
        Connect4TrieNode current = root;
        
        for (int col : moveHistory) {
            // Validate input moves are within the board constraints
            if (col < 0 || col > 6) {
                throw new IllegalArgumentException("Invalid Connect 4 column: " + col);
            }
            
            // If the path doesn't exist, create a new node
            if (current.children[col] == null) {
                current.children[col] = new Connect4TrieNode();
            }
            // Move to the next node
            current = current.children[col];
        }
        
        // Mark the final node representing this full sequence
        current.isEndGame = true;
    }

    /**
     * Searches for a precise match of a move sequence.
     * @return true if the history exists completely in the Trie.
     */
    public boolean searchSequence(int[] moveHistory) {
        Connect4TrieNode node = searchNode(moveHistory);
        return node != null && node.isEndGame;
    }

    /**
     * Checks if any previously recorded game starts with this specific prefix sequence.
     * Useful for opening book lookups or avoiding repetitive board state evaluation.
     */
    public boolean startsWithPrefix(int[] moveHistory) {
        return searchNode(moveHistory) != null;
    }

    // Helper method to traverse the Trie based on a history path
    private Connect4TrieNode searchNode(int[] moveHistory) {
        Connect4TrieNode current = root;
        for (int col : moveHistory) {
            if (current.children[col] == null) {
                return null; // Sequence path not found
            }
            current = current.children[col];
        }
        return current;
    }
}
