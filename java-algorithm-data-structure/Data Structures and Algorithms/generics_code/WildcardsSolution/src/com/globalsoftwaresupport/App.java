package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
	
	public static <T> void copy(List<? extends T> source, 
			List<? super T> destination) {
		for(int i=0;i<source.size();++i)
			destination.add(source.get(i));
	}
	
	public static void main(String[] args) {
		
		List<Integer> list = Arrays.asList(3,1,2,4);
		List<Integer> list2 = new ArrayList<>();
		copy(list, list2);
		System.out.println(list2);
	}
}
