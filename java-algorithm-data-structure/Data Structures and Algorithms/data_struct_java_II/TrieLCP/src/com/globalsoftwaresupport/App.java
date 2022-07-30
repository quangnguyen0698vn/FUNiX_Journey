package com.globalsoftwaresupport;

public class App {

	public static void main(String[] args) {
		
		Trie trie = new Trie();

		// these are the IP addresses of neighboring nodes
		trie.insert("243.189.345.123");
		//trie.insert("243.189.562.173");
		//trie.insert("243.145.111.173");
		//trie.insert("243.189.123.763");
		// this is the IP address of the packet
		trie.insert("243.189.345.221");	
		
		System.out.println(trie.longestCommonPrefix());
		
	
	}
}
