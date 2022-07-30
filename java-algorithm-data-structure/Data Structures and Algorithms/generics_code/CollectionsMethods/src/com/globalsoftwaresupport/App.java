package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {	
	
	public static void main(String[] args) {
	
		List<Integer> nums = new ArrayList<>();
		
		nums.add(1);
		nums.add(10);
		nums.add(5);
		nums.add(8);
		nums.add(1);
		nums.add(3);
		
		// we can reverse the order
		// Collections.reverse(nums);
		
		// min and max finding
		System.out.println(Collections.max(nums));
		
		// we can shuffle the underlying collection - bogo sort
		// Collections.shuffle(nums);
		
		//Collections.rotate(nums, 1);
		
		// replace the items
		Collections.replaceAll(nums, 1, 10);
		
		System.out.println(nums);
	}
}