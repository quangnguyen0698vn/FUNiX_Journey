package com.globalsoftwaresupport;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class App {	
	
	public static void main(String[] args) {
			
		Deque<Integer> stack = new ArrayDeque<>();
		
		long now = System.currentTimeMillis();
		
		for(int i=0;i<500000;i++)
			stack.push(i);
		
		while(!stack.isEmpty())
			stack.pop();
		
		System.out.println("Time taken with ArrayDeque: " + 
				(System.currentTimeMillis()-now) + "ms");
		
		Stack<Integer> stack2 = new Stack<>();
		
		now = System.currentTimeMillis();
		
		for(int i=0;i<500000;i++)
			stack2.push(i);
		
		while(!stack2.isEmpty())
			stack2.pop();
		
		System.out.println("Time taken with Stack: " + 
				(System.currentTimeMillis()-now) + "ms");
	}
}





