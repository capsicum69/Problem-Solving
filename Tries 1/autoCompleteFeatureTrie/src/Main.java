import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    public class TrieNode {
        Map<Character, TrieNode> children;
        char character;
        boolean isWord;

        public TrieNode(char character) {
            this.character = character;
            children = new HashMap<>();
        }

        public TrieNode() {
            children = new HashMap<>();
        }

        public void insert(String word) {
            if (word == null || word.isEmpty())
                return;
            char firstChar = word.charAt(0);
            TrieNode child = children.get(firstChar);
            if (child == null) {
                child = new TrieNode(firstChar);
                children.put(firstChar, child);
            }

            if (word.length() > 1)
                child.insert(word.substring(1));
            else
                child.isWord = true;
        }

    }

    TrieNode root;

    public Trie(List<String> words) {
        root = new TrieNode();
        for (String word : words)
            root.insert(word);

    }

    public boolean find(String prefix, boolean exact) {
        TrieNode lastNode = root;
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.children.get(c);
            if (lastNode == null)
                return false;
        }
        return !exact || lastNode.isWord;
    }

    public boolean find(String prefix) {
        return find(prefix, false);
    }

    public void suggestHelper(TrieNode node, List<String> suggestions, StringBuffer currentWord) {
        if (node.isWord) {
            suggestions.add(currentWord.toString());
        }

        if (node.children == null || node.children.isEmpty())
            return;

        for (TrieNode child : node.children.values()) {
            suggestHelper(child, suggestions, currentWord.append(child.character));
            currentWord.setLength(currentWord.length() - 1);
        }
    }

    public List<String> suggest(String prefix) {
        List<String> suggestions = new ArrayList<>();
        TrieNode lastNode = root;
        StringBuffer currentWord = new StringBuffer();
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.children.get(c);
            if (lastNode == null)
                return suggestions;
            currentWord.append(c);
        }
        suggestHelper(lastNode, suggestions, currentWord);
        return suggestions;
    }

}
