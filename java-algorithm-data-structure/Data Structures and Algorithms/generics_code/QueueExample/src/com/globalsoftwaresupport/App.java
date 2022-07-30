package com.globalsoftwaresupport;

import java.util.LinkedList;
import java.util.Queue;

public class App {	
	
	public static void main(String[] args) {
		
		Queue<String> queue = new LinkedList<>();
		
		// FIFO - O(1)
		queue.add("Adam");
		queue.add("Kevin");
		queue.add("Ana");
		queue.add("Katy");
		
		while(!queue.isEmpty()) {
			System.out.println(queue.remove());
		}
	}
}





