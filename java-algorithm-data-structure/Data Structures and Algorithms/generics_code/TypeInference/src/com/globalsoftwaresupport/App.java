package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.List;

public class App {	
	
	static <T> List<T> add(List<T> list, T item1, T item2) {
		list.add(item1);
		list.add(item2);
		return list;
	}

	public static void main(String[] args) {
		
		List<Character> list1 = add(new ArrayList<>(), 'a', 'b');
		System.out.println(list1);
		
	}

}
