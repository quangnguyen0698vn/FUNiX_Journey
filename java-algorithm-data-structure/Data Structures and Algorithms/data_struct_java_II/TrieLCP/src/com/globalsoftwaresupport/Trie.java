package com.globalsoftwaresupport;

public class Trie {

	private Node root;
	private int lcpIndex;
	
	public Trie() {
		// representing the empty string
		root = new Node("");
	}
	
	public String longestCommonPrefix() {
		
		Node node = root;
		String lcp = "";
		
		// keep considering the child while the node has just a single child
		while(numOfChildren(node)==1 && !node.isLeaf()) {
			node = node.getChild(lcpIndex);
			// we get the char out of the ASCII value
			lcp = lcp + String.valueOf((char) (lcpIndex));
		}
		
		return lcp;	
	}

	// is to consider all the children that is not NULL
	// count the number of non NULL children
	private int numOfChildren(Node node) {
		
		int numOfChildren = 0;
		
		for(int i=0;i<node.getChildren().length;++i) {
			if(node.getChild(i)!=null) {
				numOfChildren++;
				lcpIndex = i;
			}
		}
		
		return numOfChildren;
	}

	public void insert(String key) {
		
		Node tempNode = root;
	
		// we have to consider all the letters in the key
		for(int i=0;i<key.length();++i) {
			// this is how we get the letter at the given index
			char c = key.charAt(i);
			// we have to deal with ASCII values
			// in our ASCII table the first letter is 'a'
			int asciiIndex = c;
			
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
	}
	
	public boolean search(String key) {
		
		Node tempNode = root;

		for(int i=0;i<key.length();++i) {
			// this is how we get the letter at the given index
			char c = key.charAt(i);
			// we have to deal with ASCII values
			// in our ASCII table the first letter is 'a'
			int asciiIndex = c;
			
			if (tempNode.getChild(asciiIndex) == null) {
				// search miss as the letter with index (asciiIndex) is not present in the trie
				return false;
			} else {
				// because the given node exists in the trie
				tempNode = tempNode.getChild(asciiIndex);
			}
		}
		
		if(!tempNode.isLeaf()) return false;
		
		return true;
	}
}
