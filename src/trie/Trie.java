package trie;

import java.util.HashMap;

class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isWord;
    public TrieNode(){
        this.children = new HashMap<>();
    }
}

public class Trie {
    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode current = root;

        for (char l : word.toCharArray()) {
            current = current.children.computeIfAbsent(l, c -> new TrieNode());
        }
        current.isWord = true;
    }

    public boolean find(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isWord;
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isWord) {
                return false;
            }
            current.isWord = false;
            return current.children.isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isWord;

        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.isEmpty();
        }
        return false;
    }
}

class Main {
    public static void main(String[] args) {
        // construct a new Trie node
        Trie head = new Trie();

        head.insert("techie");
        head.insert("techi");
        head.insert("tech");

        System.out.println(head.find("tech"));            // true
        System.out.println(head.find("techi"));           // true
        System.out.println(head.find("techie"));          // true
        System.out.println(head.find("techiedelight"));   // false

        head.insert("techiedelight");

        System.out.println(head.find("tech"));            // true
        System.out.println(head.find("techi"));           // true
        System.out.println(head.find("techie"));          // true
        System.out.println(head.find("techiedelight"));   // true
    }
}