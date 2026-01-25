package ca.umontreal.IFT2015.trees;

public class TrieApp {
    public static void main(String[] args) {

	// example with MapTrie
	System.out.println( "Example with MapTrie" );
	MapTrie mapTrie = new MapTrie();
    
	// Insert words into the Trie
	mapTrie.insert( "bear" );
	mapTrie.insert( "bell" );
	mapTrie.insert( "bid" );
	mapTrie.insert( "bull" );
	mapTrie.insert( "bullfrog" );
	mapTrie.insert( "buy" );
	mapTrie.insert( "sell" );
	mapTrie.insert( "stock" );
	mapTrie.insert( "stop" );

	// Search for words
	System.out.println( mapTrie.search( "bullfrog" ) ); // true
	System.out.println( mapTrie.search( "buy" ) ); // true
	System.out.println( mapTrie.search( "seller" ) ); // false

	// Check if words start with a prefix
	System.out.println( mapTrie.startsWith( "bul" ) ); // true
	System.out.println( mapTrie.startsWith( "st" ) ); // true
	System.out.println( mapTrie.startsWith( "sl" ) ); // false

	// Print all words in the MapTrie
	System.out.println( "Words in the MapTrie:" );
	mapTrie.printWords();

	// Delete a word and print the updated MapTrie
	mapTrie.delete( "stock" );
	System.out.println( "After deleting 'stock':" );
	mapTrie.printWords();
	
	// example with ArrayTrie
	System.out.println( "Example with MapTrie" );
	ArrayTrie arrayTrie = new ArrayTrie();
    
	// Insert words into the Trie
	arrayTrie.insert( "bear" );
	arrayTrie.insert( "bell" );
	arrayTrie.insert( "bid" );
	arrayTrie.insert( "bull" );
	arrayTrie.insert( "bullfrog" );
	arrayTrie.insert( "buy" );
	arrayTrie.insert( "sell" );
	arrayTrie.insert( "stock" );
	arrayTrie.insert( "stop" );
				       
	// Search for words
	System.out.println( arrayTrie.search( "bullfrog" ) ); // true
	System.out.println( arrayTrie.search( "buy" ) ); // true
	System.out.println( arrayTrie.search( "seller" ) ); // false

	// Check if words start with a prefix
	System.out.println( arrayTrie.startsWith( "bul" ) ); // true
	System.out.println( arrayTrie.startsWith( "st" ) ); // true
	System.out.println( arrayTrie.startsWith( "sl" ) ); // false

	// Print all words in the MapTrie
	System.out.println( "Words in the ArrayTrie:" );
	arrayTrie.printWords();

	// Delete a word and print the updated MapTrie
	arrayTrie.delete( "stock" );
	System.out.println( "After deleting 'stock':" );
	arrayTrie.printWords();
    }
}
