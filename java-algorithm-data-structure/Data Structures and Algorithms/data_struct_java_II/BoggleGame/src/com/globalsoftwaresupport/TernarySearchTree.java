package com.globalsoftwaresupport;

public class TernarySearchTree {

	private Node root;
	
	public TernarySearchTree() {
		// we just mock the data (we should insert all the valid English words)
		put("car");
		put("bus");
		put("animal");
	}
	
	public void put(String key) {
		root = put(root, key, 0);
	}
	
	private Node put(Node node, String key, int index) {
		
		char c = key.charAt(index);
		
		if(node == null)
			node = new Node(c);
		
		if(c < node.getCharacter()) {
			node.setLeftChild(put(node.getLeftChild(), key, index));
		} else if(c > node.getCharacter()) {
			node.setRightChild(put(node.getRightChild(), key, index));
		} else if(index < key.length()-1) {
			node.setMiddleChild(put(node.getMiddleChild(), key, index+1));
		} else {
			node.setLeaf(true);
		}
		
		return node;
	}

	// this is how we check whether a sequence of letters form a valid English word
	public boolean isWordInTree(String letterSequence) {
		
		Node node = get(root, letterSequence, 0);
		
		if(node == null)
			return false;
		
		return node.isLeaf();
	}
	
	private Node get(Node node, String key, int index) {
		
		if(node == null) return null;
		
		char c = key.charAt(index);
		
		if(c < node.getCharacter()) {
			return get(node.getLeftChild(), key, index);
		} else if(c > node.getCharacter()) {
			return get(node.getRightChild(), key, index);
		} else if(index < key.length() - 1) {
			return get(node.getMiddleChild(), key, index+1);
		} else {
			if(!node.isLeaf()) return null;
			return node;
		}
	}
}
