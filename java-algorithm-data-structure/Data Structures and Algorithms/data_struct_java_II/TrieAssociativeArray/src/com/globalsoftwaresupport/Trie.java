package com.globalsoftwaresupport;

public class Trie {

	private Node root;
	
	public Trie() {
		// representing the empty string
		root = new Node("");
	}
	
	public void insert(String key, int value) {
		
		Node tempNode = root;
	
		// we have to consider all the letters in the key
		for(int i=0;i<key.length();++i) {
			// this is how we get the letter at the given index
			char c = key.charAt(i);
			// we have to deal with ASCII values
			// in our ASCII table the first letter is 'a'
			int asciiIndex = c - 'a';
			
			// we have to check whether the given letter is already
			// a children of the given node
			if (tempNode.getChild(asciiIndex) == null) {
				Node node = new Node(String.valueOf(c));
				tempNode.setChild(asciiIndex, node);
				tempNode = node;
			} else {
			// the node with the given letter is already a child
				tempNode = tempNode.getChild(asciiIndex);
			}
		}
		
		tempNode.setLeaf(true);
		tempNode.setValue(value);
	}
	
	public Integer search(String key) {
		
		Node tempNode = root;

		for(int i=0;i<key.length();++i) {
			// this is how we get the letter at the given index
			char c = key.charAt(i);
			// we have to deal with ASCII values
			// in our ASCII table the first letter is 'a'
			int asciiIndex = c - 'a';
			
			if (tempNode.getChild(asciiIndex) == null) {
				// search miss as the letter with index (asciiIndex) is not present in the trie
				return null;
			} else {
				// because the given node exists in the trie
				tempNode = tempNode.getChild(asciiIndex);
			}
		}
		
		// this is when the key is not present
		if(!tempNode.isLeaf()) return null;
		
		// this is always a leaf node's value
		return tempNode.getValue();
	}
}
