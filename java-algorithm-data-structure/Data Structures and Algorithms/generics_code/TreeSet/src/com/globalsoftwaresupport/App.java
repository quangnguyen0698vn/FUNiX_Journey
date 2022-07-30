package com.globalsoftwaresupport;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class App {	
	
	public static void main(String[] args) {
	
		// O(logN)
		// MEMORY FRIENDLY !!!
		SortedSet<Integer> set = new TreeSet<>(Comparator.reverseOrder());
		
		set.add(12);
		set.add(1);
		set.add(9);
		set.add(5);
		set.add(120);
		set.add(87);
		set.add(55);
		
		Set<Integer> subSet = set.headSet(20);
		// System.out.println(set.last());
		
		for(Integer num : set)
			System.out.println(num);
	}
}