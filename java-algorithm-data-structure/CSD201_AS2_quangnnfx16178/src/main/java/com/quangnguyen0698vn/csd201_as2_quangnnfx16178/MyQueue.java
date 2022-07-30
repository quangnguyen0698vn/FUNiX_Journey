
package com.quangnguyen0698vn.csd201_as2_quangnnfx16178;

/**
 * @author quang
 */
/**
 * 
 * Generic version of the Queue class.
 *
 * @param <T> the type of the value
 * 
 */

class MyQueue<T extends Comparable<T>> {

	/**
	 * 
	 * Head node contains front node in the queue
	 * 
	 */

	private Node<T> head;

	/**
	 * 
	 * Tail node contains last node in the queue
	 * 
	 */

	private Node<T> tail;

	public MyQueue() {
		this.head = this.tail = null;
	}
	
	public MyQueue(Node<T> head, Node<T> tail) {
		this.head = head;
		this.tail = tail;
	}
	
	
	
	public void add(T data) {
		Node<T> newNode = new Node<>(data);
		if (head == null) {
			head = tail = newNode;
			return;
		}
		tail.setNext(newNode);
		tail = newNode;
	}
	
	public T pop() {
		if (this.isEmpty()) return null;
		T ret = this.getHead().getInfo();
		if (head == tail)
			head = tail = null;
		else {
			head = head.getNext();
		}
		return ret;
	}

	public Node<T> getHead() {
		return head;
	}
	
	public boolean isEmpty() {
		return head == null;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public Node<T> getTail() {
		return tail;
	}

	public void setTail(Node<T> tail) {
		this.tail = tail;
	}

	public void traverse() {
		while (!this.isEmpty())
			MyLogger.log(this.pop().toString() + "\n");
	}
	
}
