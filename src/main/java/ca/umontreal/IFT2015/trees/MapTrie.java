package ca.umontreal.IFT2015.trees;

/**
* MapTrie is an implementation of extended Trie with a Map
* 
* @author      Francois Major
* @version     1.0
* @since       October 6, 2024
*/

import java.util.HashMap;
import java.util.Map;

public class MapTrie {
    
    // a node in the Trie has children and a flag to indicate end of word
    private class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false; // true if the node represents a word
    }

    private final TrieNode root;

    // constructor
    public MapTrie() { root = new TrieNode(); }

    // add a word into the Trie
    public void insert( String word ) {
        TrieNode current = root;
        for( char c : word.toCharArray() ) {
            current = current.children.computeIfAbsent( c, k -> new TrieNode() );
        }
        current.isWord = true;
    }

    // search for a word in the Trie
    public boolean search( String word ) {
        TrieNode current = root;
        for( char c : word.toCharArray() ) {
            current = current.children.get( c );
            if( current == null ) return false; // if a character is missing, word is not found
        }
        return current.isWord; // return true if current marks a word
    }

    // check if there is any word in the Trie that starts with the given prefix
    public boolean startsWith( String prefix ) {
	TrieNode current = root;
	for( char c : prefix.toCharArray() ) {
	    current = current.children.get( c );
	    if( current == null ) return false; // prefix is not found
	}
	return true; // if we can traverse the entire prefix
    }

    // utility method to delete a word from the Trie
    public boolean delete( String word ) {
	return delete( root, word, 0 );
    }

    private boolean delete( TrieNode current, String word, int index ) {
	if( index == word.length() ) {
	    // if the word is completely traversed
	    if( !current.isWord ) return false; // word not found
	    current.isWord = false; // unmark the end of word
	    return current.children.isEmpty(); // return true if no other branches depend on this
	}

	char ch = word.charAt( index );
	TrieNode node = current.children.get( ch );
	if( node == null ) return false; // Word not found

	boolean shouldDeleteCurrentNode = delete( node, word, index + 1 );

	// Remove the mapping if true
	if( shouldDeleteCurrentNode ) {
	    current.children.remove( ch );
	    // Return true if no other children are dependent on this node
	    return current.children.isEmpty();
	}
	return false;
    }

    // utility method to print all words stored in the Trie
    public void printWords() { printWords( root, new StringBuilder() ); }

    private void printWords( TrieNode node, StringBuilder prefix ) {
	if( node.isWord ) {
	    System.out.println( prefix.toString() );
	}
	for( char c : node.children.keySet() ) {
	    prefix.append( c );
	    printWords( node.children.get( c ), prefix );
	    prefix.deleteCharAt( prefix.length() - 1 ); // backtrack
	}
    }
}
    
