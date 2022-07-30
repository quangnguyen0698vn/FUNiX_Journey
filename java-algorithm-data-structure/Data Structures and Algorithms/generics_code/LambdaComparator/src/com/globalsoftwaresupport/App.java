package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App {	
	
	public static void main(String[] args) {
	
		// LAMBDA EXPRESSIONS !!!
		List<Person> people = new ArrayList<>();
		
		people.add(new Person("Adam", 33));
		people.add(new Person("Joe", 12));
		people.add(new Person("Michael", 25));
		people.add(new Person("Ana", 27));
		people.add(new Person("Katy", 78));
		people.add(new Person("Kevin", 45));
		people.add(new Person("Adam", 12));
		people.add(new Person("Adam", 34));
		
		Collections.sort(people, Comparator.comparing(Person::getName)
				.thenComparing(Person::getAge));
		
		for(Person p : people)
			System.out.println(p.getName() + "-" + p.getAge());
	}
}