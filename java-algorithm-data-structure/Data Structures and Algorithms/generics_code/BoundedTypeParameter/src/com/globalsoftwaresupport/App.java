package com.globalsoftwaresupport;

class Person implements Comparable<Person> {

	private int age;
	private String name;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	
	@Override
	public int compareTo(Person otherPerson) {
		return Integer.compare(age, otherPerson.getAge());
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
}

public class App {
	
	public static <T extends Comparable<T>> T calculateMin(T num1, T num2) {
		
		if(num1.compareTo(num2) < 0)
			return num1;
		
		return num2;
	}
	
	public static <T extends Number> double add(T num1, T num2) {
		double result = num1.doubleValue() + num2.doubleValue();
		return result;
	}

	public static void main(String[] args) {
		
		System.out.println(add(10,5.4f));
		
	}

}
