package com.globalsoftwaresupport;

class Store<T> {
	
	private T item;
	
	public void setItem(T item) {
		this.item = item;
	}
	
	public T getItem() {
		return this.item;
	}
}

class Hashtable<K,V> {
	
	private K key;
	private V value;
	
	public Hashtable(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Hashtable [key=" + key + ", value=" + value + "]";
	}
}

public class App {

	public static void main(String[] args) {
		
		Hashtable<Integer,Integer> hashTable = new Hashtable<>(20,23);
		System.out.println(hashTable);
	}
}
