package com.globalsoftwaresupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {	
	
	public static void showAll(List<? super Number> list) {
		for(Object num : list)
			System.out.println(num);
	}
	
	public static void main(String[] args) {
		
		List<? super Integer> l1 = new ArrayList<Integer>();
		List<? super Integer> l2 = new ArrayList<Number>();
		List<? super Integer> l3 = new ArrayList<Object>();
		
		// how to read items using lower bounded wildcard
		List<Serializable> list = new ArrayList<>();
		list.add("Adam");
		list.add("Kevin");
		list.add("Ana");
		showAll(list);
		
		// insert items
		List<? super Number> nums = new ArrayList<>();
		nums.add(3);
		nums.add(3.3);
		nums.add(3.5f);
		
	}
}
