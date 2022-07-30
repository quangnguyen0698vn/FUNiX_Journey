
package com.quangnguyen0698vn.csd201_as2_quangnnfx16178;

/**
 * 
 * Generic version of the Node class.
 *
 * @param <T> the type of the value
 * 
 */

public class Node<T extends Comparable<T>> {

	/**
	 * 
	 * The info of this node
	 * 
	 */

	private T info;

	/**
	 * 
	 * The next node
	 * 
	 */

	private Node next;

	/**
	 * 
	 * Default constructor
	 * 
	 */

	public Node() {

	}

	/**
	 * 
	 * Constructor with info
	 * @param info The info of this node
	*/
	
	public Node(T info) {
		this.info = info;
		this.next = null;
	}
	
	/**
	 * 
	 * Constructor with info and next node
	 * @param info The info of this node
	 * @param next The next Node of this node
	 * 
	 */

	public Node(T info, Node next) {
		this(info);
		this.next = next;
	}

	/**
	 * 
	 * Overriding to convert this node to String
	 * 
	 */

	@Override

	public String toString() {
		return info.toString();
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	
}
