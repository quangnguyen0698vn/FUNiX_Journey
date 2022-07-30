package com.globalsoftwaresupport;

public class App {

	public static void main(String[] args) {
		
		Trie trie = new Trie();

		trie.insert("adam");
		trie.insert("michael");
		trie.insert("anna");
		trie.insert("kevin");
		trie.insert("daniel");
		trie.insert("justin");
		
		for(String s : trie.sort())
			System.out.println(s);
	}
}
