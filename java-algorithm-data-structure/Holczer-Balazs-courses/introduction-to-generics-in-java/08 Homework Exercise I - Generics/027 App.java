package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.List;

interface Algorithm {
	public void execute();
}

class SearchAlgorithm implements Algorithm {

	@Override
	public void execute() {
		System.out.println("Executing search algorithm...");
	}
	
}

class SortingAlgorithm implements Algorithm {

	@Override
	public void execute() {
		System.out.println("Executing sorting algorithm...");
	}
	
}

class GraphAlgorithm implements Algorithm {

	@Override
	public void execute() {
		System.out.println("Executing graph algorithm...");
	}
	
}

class Library<T extends Algorithm> {
	
	private List<T> algorithms;
	
	public Library() {
		this.algorithms = new ArrayList<>();
	}
	
	public void add(T algorithm) {
		algorithms.add(algorithm);
	}
	
	public T getLast() {
		
		if(algorithms.size()<=0) return null;
		
		T actualAlgorithm = algorithms.remove(algorithms.size()-1);
		return actualAlgorithm;
	}
}


public class App {

	public static void main(String[] args) {
		
		Library<Algorithm> library = new Library<>();
		library.add(new SearchAlgorithm());
		library.add(new SortingAlgorithm());
		library.add(new GraphAlgorithm());
		
		Algorithm item = library.getLast();
		
		while(item!=null) {
			item.execute();
			item = library.getLast();
		}
		
	}
}
