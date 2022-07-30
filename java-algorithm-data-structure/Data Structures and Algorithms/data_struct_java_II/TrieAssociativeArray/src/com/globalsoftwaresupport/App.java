package com.globalsoftwaresupport;

public class App {

	public static void main(String[] args) {
		
		Trie trie = new Trie();
		
		trie.insert("air", 5);
		trie.insert("apple", 10);
		trie.insert("approve", 6);
		trie.insert("bee", 4);
		
		System.out.println(trie.search("airstrike"));
	}
}
