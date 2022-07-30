package com.globalsoftwaresupport;

import java.util.HashSet;
import java.util.Set;

public class App {	
	
	public static void main(String[] args) {
	
		// initial capacity = 16
		// hashCode() function (hash-function) transforms the input into an array index
		// O(1) + memory heavy
		// there may be collisions - O(logN)
		Set<String> set1 = new HashSet<>();
		
		set1.add("Adam");
		set1.add("Mary");
		set1.add("Katy");
		set1.add("Ana");
		
		Set<String> set2 = new HashSet<>();
		
		set2.add("Adam");
		set2.add("Ana");
		
		set1.removeAll(set2);
		
		for(String s : set1)
			System.out.println(s);
	}
}