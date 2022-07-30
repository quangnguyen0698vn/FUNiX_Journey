
package com.quangnguyen0698vn.csd201_as2_quangnnfx16178;

/**
 * @author quang
 */
/**
 * 
 * Generic version of the Stack class.
 *
 * @param <T> the type of the value
 * 
 */

class MyStack<T extends Comparable<T>> {

	/**
	 * 
	 * Head node contains front node in the stack
	 * 
	 */

	private Node<T> head;
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void push(T data) {
		Node<T> newNode = new Node<>(data);
		if (head == null) {
			head = newNode;
		}
		else {
			newNode.setNext(head);
			head = newNode;
		}
	}
	
	public T pop() {
		if (this.isEmpty()) return null;
		T ret = this.getHead().getInfo();
		head = head.getNext();
		return ret;
	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}
	
	public void traverse() {
		while (!this.isEmpty())
			MyLogger.log(this.pop().toString()+"\n");
	}
	
}
