import java.lang.*;
import java.util.*;

class Node {
    String word;
    Map<Character, Node> neighbours;

    Node() {
        this.word = null;
        neighbours = new HashMap<>();
    }
}

class Trie {
    Node root;
    List<String> matchedWords;

    Trie() {
        root = new Node();
        matchedWords = new LinkedList<>();
    }

    private boolean isSafe(int row, int col, int maxRow, int maxCol) {
        return row >= 0 && row < maxRow && col >= 0 && col < maxCol;
    }

    public void makeTrie(String[] words) {
        for (String word : words) {
            Node traverse = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!traverse.neighbours.containsKey(ch))
                    traverse.neighbours.put(ch, new Node());
                traverse = traverse.neighbours.get(ch);
            }
            traverse.word = word;
        }
    }

    public void search(char[][] board, int row, int col, Node traverse) {
        if (row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col] == '1')
            return;

        char current = board[row][col];
        if (!traverse.neighbours.containsKey(current))
            return;

        traverse = traverse.neighbours.get(current);
        if (traverse.word != null) {
            matchedWords.add(traverse.word);
            traverse.word = null;
        }

        board[row][col] = '1';
        search(board, row + 1, col, traverse);
        search(board, row - 1, col, traverse);
        search(board, row, col + 1, traverse);
        search(board, row, col - 1, traverse);
        board[row][col] = current;
    }
}

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Trie t = new Trie();
        t.makeTrie(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                t.search(board, i, j, t.root);
            }
        }

        return t.matchedWords;
    }
}
