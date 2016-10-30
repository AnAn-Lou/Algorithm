public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        // empty input
        if (word == null || word.length() == 0) {
            return;
        }
        // insert
        TrieNode curr = root;
        char[] arr = word.toCharArray();
        for (char ch : arr) {
            int i = ch - 'a';
            if (curr.kids[i] == null) {
                curr.kids[i] = new TrieNode();
            }
            curr = curr.kids[i];
        }
        curr.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        // empty input
        if (word == null || word.length() == 0) {
            return false;
        }
        // search
        TrieNode curr = root;
        char[] arr = word.toCharArray();
        for (char ch : arr) {
            int i = ch - 'a';
            if (curr.kids[i] == null) {
                return false;
            }
            curr = curr.kids[i];
        }
        if (curr.isWord) {
            return true;
        }
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        // empty input
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        // search prefix
        TrieNode curr = root;
        char[] arr = prefix.toCharArray();
        for (char ch : arr) {
            int i = ch - 'a';
            if (curr.kids[i] == null) {
                return false;
            }
            curr = curr.kids[i];
        }
        return true;
    }

    private class TrieNode {
        // Initialize your data structure here.
        TrieNode[] kids;
        boolean isWord;
        public TrieNode() {
            kids = new TrieNode[26];
            isWord = false;
        }
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
