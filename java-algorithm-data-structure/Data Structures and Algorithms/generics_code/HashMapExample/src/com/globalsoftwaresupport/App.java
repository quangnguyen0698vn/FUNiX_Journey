package com.globalsoftwaresupport;

import java.util.HashMap;
import java.util.Map;

public class App {	
	
	public static void main(String[] args) {
			
		Map<Integer, String> map = new HashMap<>();
		
		// insert into the map O(1) IF THERE IS NO COLLISION
		map.put(1, "Adam");
		map.put(2, "Kevin");
		map.put(3, "Ana");
		map.put(40, "Lucy");
		
		// remove items in O(1)
		map.remove(60);
		
		// we can retrieve items based on keys O(1)
		// NULL keys
		// System.out.println(map.get(null));
		
		for(Map.Entry<Integer,String> entry : map.entrySet())
			System.out.println(entry.getKey() + " - " + entry.getValue());
		
	}
}