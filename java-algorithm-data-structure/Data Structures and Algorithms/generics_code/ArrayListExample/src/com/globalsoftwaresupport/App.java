package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.List;

public class App {	
	
	public static void main(String[] args) {
		
		// resizing the array takes O(N)
		List<String> names = new ArrayList<>();
		
		names.add("Kevin");
		names.add("Adam");
		names.add("Ana");
		
		// random indexing in O(1)
		// System.out.println(names.get(0));
		
		// insert item into a given index - O(N)
		names.add(0, "Daniel");
		names.remove(0);
		
		// contains will check whether the item is present in the array O(N)
		System.out.println(names.contains("Katy"));
		
		Object[] o = names.toArray();
		
		// ARRAYLIST (ARRAYS) ARE FAST IF WE MANIPULATE THE LAST ITEM
			
		// because of the Iterable interface
		for(Object s : o)
			System.out.println(s);
	}
}





