package com.globalsoftwaresupport;

import java.util.Arrays;
import java.util.Collections;

public class App {	
	
	public static void main(String[] args) {
	
		// QuickSort - to sort primitive types (int, float etc.)
		// MergeSort - to sort reference types (Integer, String ...)
		
		int[] nums = {1, 10, 5, 2, -5, 12, 14, 0, 1, 2};
		String[] names = {"Kevin","Daniel","Katy","Ana","Joe","Adam"};
		
		Arrays.sort(names);
		
		for(String name : names)
			System.out.print(name + " ");
	}
}