package com.globalsoftwaresupport;

import java.util.Stack;

public class App {	
	
	public static void main(String[] args) {
		
		Stack<String> names = new Stack<>();
		
		names.push("Adam");
		names.push("Joe");
		names.push("Ana");
		names.push("Daniel");
		names.push("Katy");
		
		while(!names.isEmpty()) {
			System.out.println(names.pop());
		}
	}
}





