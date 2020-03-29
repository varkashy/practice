package dataStructures.trie;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.List;

public class SearchInsertDeleteTrie {

	private static final TrieNode root = new TrieNode();

	public static void insertIntoTrie(String word){
		//find at which level do we get the eventual leaf node
		int level = word.length();
		/*
			iterate through each character in word.
			If the char is not already set, mark it as new trie node and go down that path
			if it is already set , do nothing
			Mark leaf node as isEndOfWord = true
		 */
		TrieNode crawlNode = root;
		for(int index=0;index<level;index++){
			int charIndex = word.charAt(index) - 'a';
			//check if this is already set
			if(crawlNode.children[charIndex] == null){
				crawlNode.children[charIndex] = new TrieNode();
			}
			crawlNode = crawlNode.children[charIndex];
		}
		//Once you reach the end of traversal for a word, mark last node as end of word
		crawlNode.isEndOfWord = true;
	}

	public static boolean searchIfAWordExistsInTrie(String word){
		/*
		Iterate through the word for its entire length
		If for a given char, corresponding index is not set, return false.
		If for end of length, node is not marked as end of word, return false;
		 */
		//Get a crawl node to iterate over the given data
		TrieNode crawlNode = root;
		int level = word.length();
		for(int index = 0;index<level;index++){
			if(crawlNode.children[word.charAt(index)-'a']==null){
				return false;
			}
			crawlNode = crawlNode.children[word.charAt(index)-'a'];
		}
		return (crawlNode!=null && crawlNode.isEndOfWord);
	}

	public static void displayAllWordsInTrie(){
		/*
		This will be depth first search, any word is complete if for a node isEndOfWord is true
		 */
		//Get a crawlNode
		List<String> allWords = new ArrayList<>();
		//We would want to do this recursively by calling a recursive function for each node
		findAllWordsRecursively(root,new StringBuilder(),allWords);
		for(String wordsInTrie:allWords){
			System.out.println(wordsInTrie);
		}
	}

	private static void  findAllWordsRecursively(final TrieNode root, StringBuilder words, List<String> allWords) {

		if(root == null){
			return;
		}

		if(root.isEndOfWord){
			allWords.add(words.toString());
		}

		for(int index = 0;index <26; index ++){
			if(root.children[index]!=null){
				StringBuilder childStringBuilder = new StringBuilder(words.toString()).append((char)(index+'a'));
				findAllWordsRecursively(root.children[index],childStringBuilder,allWords);
			}
		}
	}

	public static List<String> autoCorrectForGivenString(String inputString){
		List<String> autoCorrectOptions = new ArrayList<>();
		//Search for given String and append
		if(searchIfAWordExistsInTrie(inputString)){
			autoCorrectOptions.add(inputString);
		}
		//find the node to start autosuggesting
		TrieNode crawlNode = root;
		for(int index=0;index<inputString.length();index++){
			if(crawlNode.children[inputString.charAt(index)-'a']==null){
				return autoCorrectOptions;
			}
			else{
				crawlNode = crawlNode.children[inputString.charAt(index)-'a'];
			}
		}
		if(crawlNode == null){
			return  autoCorrectOptions;
		}
		else{
			getStringAutoCorrected(crawlNode,new StringBuilder(inputString), autoCorrectOptions);
		}
		return autoCorrectOptions;
	}

	private static void getStringAutoCorrected(TrieNode root, StringBuilder word,final List<String> autoCorrectOptions) {
		if(root == null){
			return;
		}
		if(root.isEndOfWord){
			autoCorrectOptions.add(word.toString());
		}
		for(int index=0;index<26;index++){
			if(root.children[index]!=null){
				StringBuilder childWords = new StringBuilder(word.toString()).append((char)(index+'a'));
				getStringAutoCorrected(root.children[index],childWords,autoCorrectOptions);
			}
		}
	}

	/**
	 * Delete a given word from Trie
	 * 4 scenarios arise:
	 * 1. Word not present in Trie -> do nothing
	 * 2. Word is unique in Trie -> i.e. is not prefix of another word, nor has a prefix -> delete all trie nodes for the word's char
	 * 3. Word has a prefix -> go from end, remove all nodes for word till find the first leaf of longest prefix i.e. encounter isEndOfWord = true for a node
	 * 4. Word is a prefix -> mark the node that has isEndOfWord true to false
	 * @param word
	 */
	public static void delete(String word){
		//Take a crawl node
		TrieNode crawlNode = root;
		//For given word find if the word exists
		//Scenario 1, word doesnt exist return
		if(!searchIfAWordExistsInTrie(word)){
			return;
		}
		root.children[word.charAt(0)-'a'] = deleteWordFromTrie(root.children[word.charAt(0)-'a'],word,0,false);
	}

	private static TrieNode deleteWordFromTrie(TrieNode node,String word,int level,boolean hasPrefix) {
		if(node == null){
			return null;
		}
		boolean isChildren = false;
		if(level==word.length()-1){
			for(int i=0;i<26;i++){
				if(node.children[i]!=null){
					isChildren = true;
					break;
				}
			}
			if(isChildren){
				node.isEndOfWord = false;
				return node;
			}
			return null;
		}
		if(level < word.length()-1){
			if(node.isEndOfWord){
				node.children[word.charAt(level+1)-'a'] = deleteWordFromTrie(node.children[word.charAt(level+1)-'a'],word,level+1,true);
			}
			else{
				node.children[word.charAt(level+1)-'a'] = deleteWordFromTrie(node.children[word.charAt(level+1)-'a'],word,level+1,false);
			}
		}
		return node;
	}

	public static void main(String[] args) {
		// Input keys (use only 'a' through 'z' and lower case)
		String[] keys = {"the", "a", "there", "answer", "any",
				"by", "bye", "their","sam","samuel","pax"};
		String[] output = {"Not present in trie", "Present in trie"};

		// Construct trie
		int i;
		for (i = 0; i < keys.length ; i++)
			insertIntoTrie(keys[i]);

//		// Search for different keys
//		if(searchIfAWordExistsInTrie("the"))
//			System.out.println("the --- " + output[1]);
//		else System.out.println("the --- " + output[0]);
//
//		if(searchIfAWordExistsInTrie("these"))
//			System.out.println("these --- " + output[1]);
//		else System.out.println("these --- " + output[0]);
//
//		if(searchIfAWordExistsInTrie("their"))
//			System.out.println("their --- " + output[1]);
//		else System.out.println("their --- " + output[0]);
//
//		if(searchIfAWordExistsInTrie("thaw"))
//			System.out.println("thaw --- " + output[1]);
//		else System.out.println("thaw --- " + output[0]);

//		displayAllWordsInTrie();
//		System.out.println("--------------");
//		delete("a");
//		delete("samuel");
//		delete("sam");
//		delete("pax");

		displayAllWordsInTrie();
		System.out.println("Autocorrect options for b");
		final List<String> stringList = autoCorrectForGivenString("b");
		stringList.forEach(string -> System.out.println(string));
		System.out.println("Autocorrect options for sa");
		final List<String> stringList2 = autoCorrectForGivenString("sa");
		stringList2.forEach(string -> System.out.println(string));
		System.out.println("Autocorrect options for t");
		final List<String> stringList3 = autoCorrectForGivenString("t");
		stringList3.forEach(string -> System.out.println(string));
	}
}


class TrieNode{
	final TrieNode[] children = new TrieNode[26];
	boolean isEndOfWord;
}
