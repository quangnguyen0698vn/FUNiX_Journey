package com.globalsoftwaresupport;

public class TernarySearchTree {

	// we have access to the root node
	private Node root;
	
	// associative array - we get a value based on a key
	public Integer get(String key) {
		
		Node node = get(root, key, 0);
		
		// there may be a search miss
		if(node == null) return null;
		
		return node.getValue();
	}
	
	private Node get(Node node, String key, int index) {
		
		// base case
		if(node == null) return null;
		
		// we consider the letters in the key one by one
		char c = key.charAt(index);
		
		if(c < node.getCharacter()) {
			return get(node.getLeftChild(), key, index);
		} else if(c > node.getCharacter()) {
			return get(node.getRightChild(), key, index);
		} else if(index < key.length() - 1) {
			return get(node.getMiddleChild(), key, index+1);
		} else {
			// we have considered all the letters
			if(!node.isLeaf()) return null;
			// we have found the node
			return node;
		}
	}

	// we implement associative array
	public void put(String key, int value) {
		root = insert(root, key, value, 0);
	}

	private Node insert(Node node, String key, int value, int index) {
		
		// actual index of the key
		char c = key.charAt(index);
		
		// if the node is a NULL
		if(node == null)
			node = new Node(c);
		
		// check the location of the new node
		if(c < node.getCharacter()) {
			node.setLeftChild(insert(node.getLeftChild(), key, value, index));
		} else if(c > node.getCharacter()) {
			node.setRightChild(insert(node.getRightChild(), key, value, index));
		// this is not the last letter AND this is the middle child
		} else if(index < key.length()-1) {
			node.setMiddleChild(insert(node.getMiddleChild(), key, value, index+1));
		} else {
		// this node is the lead node
			node.setLeaf(true);
			// we assign the values to the leaf nodes (last letters of the keys)
			node.setValue(value);
		}
		
		return node;
	}
	
	public void traverse() {
		if(root!=null) 
			traverse(root, "");
	}

	private void traverse(Node node, String s) {
		
		// left + root + right RECURSIVE MANNER
		// we hit a leaf node
		if (node.getMiddleChild() == null || node.getValue() != 0)
			System.out.println(s + node.getCharacter() + ": " + node.getValue());
		
		// recursively to the left
		if(node.getLeftChild() != null)
			traverse(node.getLeftChild(), s);
		
		// middle child
		if (node.getMiddleChild() != null)
			traverse(node.getMiddleChild(), s + node.getCharacter());
		
		// right child
		if(node.getRightChild() != null)
			traverse(node.getRightChild(), s);
	}
 }





