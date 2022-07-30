package com.globalsoftwaresupport;

import java.util.LinkedList;
import java.util.List;

public class App {	
	
	public static void main(String[] args) {
		
		// USUALLY WE USE LINKED LIST (MORE MEMEORY !!!)
		LinkedList<Integer> list = new LinkedList<>();
		
		// O(1)
		list.addFirst(1);
		list.addFirst(10);
		list.addFirst(5);
		list.addFirst(3);
		
		list.removeLast();
		
		System.out.println("isEmpty(): " + list.isEmpty());
		
		for(Integer num : list)
			System.out.println(num);
	}
}





