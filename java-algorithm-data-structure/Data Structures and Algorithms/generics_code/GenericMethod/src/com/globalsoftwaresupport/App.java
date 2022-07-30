package com.globalsoftwaresupport;

public class App {

	public static void main(String[] args) {
		
		GenericMethod method = new GenericMethod();
		// method.printItems("Adam", 3);
		// System.out.println("Item is returned: " + method.showItem("Adam"));

		Integer[] nums = {1,2,5,4,3};
		String[] names = {"Adam","Ana","Daniel","Kevin","Michael"};
		method.showItems(names);
	}

}
