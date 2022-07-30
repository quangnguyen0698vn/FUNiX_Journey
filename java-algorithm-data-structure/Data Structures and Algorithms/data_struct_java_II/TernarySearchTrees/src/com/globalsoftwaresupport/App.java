package com.globalsoftwaresupport;

public class App {

	public static void main(String[] args) {
		
		TernarySearchTree tree = new TernarySearchTree();
		
		tree.put("car", 10);
		tree.put("bus", 5);
		tree.put("banana", 7);
		tree.put("city", 20);
		tree.put("person", 2);
		
		tree.traverse();
	}
}
