package ca.umontreal.IFT2015.trees;

/**
* ArrayTrie is an implementation of extended Trie with an array
* 
* @author      Francois Major
* @version     1.0
* @since       October 6, 2024
*/

public class ArrayTrie {

    // a node in the Trie has an array for children and a flag to indicate the end of a word
    private class TrieNode {
        TrieNode[] children = new TrieNode[128]; // array for ASCII characters
        boolean isWord = false; // true if the node represents a word
    }

    private final TrieNode root;

    // constructor
    public ArrayTrie() { root = new TrieNode(); }

    // add a word into the Trie
    public void insert( String word ) {
        TrieNode current = root;
        for( char c : word.toCharArray() ) {
            if( current.children[c] == null ) {
                current.children[c] = new TrieNode();
            }
            current = current.children[c];
        }
        current.isWord = true;
    }

    // search for a word in the Trie
    public boolean search( String word ) {
        TrieNode current = root;
        for( char c : word.toCharArray() ) {
            if( current.children[c] == null ) {
                return false; // if a character is missing, the word is not found
            }
            current = current.children[c];
        }
        return current.isWord; // return true if the current node marks a word
    }

    // check if there is any word in the Trie that starts with the given prefix
    public boolean startsWith( String prefix ) {
        TrieNode current = root;
        for( char c : prefix.toCharArray() ) {
            if( current.children[c] == null ) {
                return false; // Prefix is not found
            }
            current = current.children[c];
        }
        return true; // If we can traverse the entire prefix
    }

    // utility method to delete a word from the Trie
    public boolean delete( String word ) {
        return delete( root, word, 0 );
    }

    private boolean delete( TrieNode current, String word, int index ) {
        if( index == word.length() ) {
            // if the word is completely traversed
            if( !current.isWord ) {
                return false; // word not found
            }
            current.isWord = false; // unmark the end of the word
            return hasNoChildren( current ); // return true if no other branches depend on this
        }

        char ch = word.charAt( index );
        TrieNode node = current.children[ch];
        if( node == null ) {
            return false; // Word not found
        }

        boolean shouldDeleteCurrentNode = delete( node, word, index + 1 );

        // remove the reference if true
        if( shouldDeleteCurrentNode ) {
            current.children[ch] = null;
            // return true if no other children are dependent on this node
            return hasNoChildren( current );
        }
        return false;
    }

    // utility method to check if a node has no children
    private boolean hasNoChildren( TrieNode node ) {
        for( TrieNode child : node.children ) {
            if( child != null ) {
                return false;
            }
        }
        return true;
    }

    // utility method to print all words stored in the Trie
    public void printWords() {
        printWords( root, new StringBuilder() );
    }

    private void printWords( TrieNode node, StringBuilder prefix ) {
        if( node.isWord ) {
            System.out.println( prefix.toString() );
        }
        for( int i = 0; i < node.children.length; i++ ) {
            if( node.children[i] != null ) {
                prefix.append( (char) i ); // append the corresponding character for the index
                printWords( node.children[i], prefix );
                prefix.deleteCharAt( prefix.length() - 1 ); // backtrack
            }
        }
    }
}
