
package com.quangnguyen0698vn.csd201_lab.lab05;

/**
 * @author quang
 * 
 * Viết chương trình chuyển đổi số nguyên ở hệ đếm thập phân sang hệ đếm nhị phân sử dụng cấu trúc dữ liệu stack (stack được cài đặt bằng Linked List)

Gợi ý:

- Sau đây là gợi ý về Input và output:

Input: 5

Output: 101
 */
public class Lab05App {
	
	public static void main(String args[]) {
		int x = 5;
		MyStack t = new MyStack();
//		t.push(100);
//		t.push(200);
//		t.push(300);
//		while (!t.isEmpty())
//			System.out.println(t.pop());
		t.convertToBinary(x);
	}
}

class MyStack {
	private Node head;
	
	public MyStack() {
		head = null;
	}
	
	public void push(int x) {
		Node newNode = new Node(x);
		if (head == null)
			head = newNode;
		else {
			newNode.setNext(head);
			head = newNode;
		}
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int pop() {
		int ret = head.getData();
		head = head.getNext();
		return ret;
	}
	
	public void convertToBinary(int x) {
		if (x == 0)
			this.push(0);
		else
		while (x > 0) {
			this.push(x%2);
			x /= 2;
		}
		
		while (!this.isEmpty()) {
			System.out.print(this.pop());
		}
	}
}

class Node {
	private int data;
	private Node next;
	
	public Node(int data) {
		this.data = data;
		this.next = null;
	}
	
	public Node(int data, Node next) {
		this(data);
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return Integer.toString(data);
	}
	
}
