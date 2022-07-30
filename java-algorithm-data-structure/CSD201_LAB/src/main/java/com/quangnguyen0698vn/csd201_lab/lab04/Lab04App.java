
package com.quangnguyen0698vn.csd201_lab.lab04;

/**
 * @author quang 
 * Viết một chương trình để tạo ra một danh sách liên kết đơn mà trong đó dữ
 * liệu của mỗi nút (node) là thông tin của một người (tên, tuổi), khi thêm một phần tử
 * (node) thì thêm vào cuối của danh sách. Hãy liệt kê ra thông tin của mọi phần tử trong
 * danh sách, sau đó hãy sắp xếp danh sách theo tên và hiển thị ra màn hình.
 * 
 * Gợi ý:
 * 
 * - Sau đây là gợi ý về input, output
 * 
 * input:
 * 
 * ten[] = {"HOA","HA","LAN","NOI","MUA","NAY"}; tuoi[] = {25,17,26,19,23,21};
 * 
 * output:
 * 
 * Traverse:
 * 
 * HOA 25 HA 17 LAN 26 NOI 19 MUA 23 NAY 21
 * 
 * sortByname:
 * 
 * HA 17 HOA 25 LAN 26 MUA 23 NAY 21 NOI 19
 */
public class Lab04App {

	public static void main(String args[]) {
		String[] a = { "HOA", "HA", "LAN", "NOI", "MUA", "NAY" };
		int[] b = { 25, 17, 26, 19, 23, 21 };
		MyList t = new MyList();
		t.addMany(a, b);

		System.out.println("Traverse:");
		t.traverse();

		System.out.println("Sort by name:");
		t.sortByName();
		t.traverse();
		System.out.println();
	}

}

class MyList {

	private Node head, tail;

	public void setHead(Node head) {
		this.head = head;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public Node getHead() {
		return head;
	}

	public Node getTail() {
		return tail;
	}

	public MyList() {
		head = tail = null;
	}

	public void clear() {
		head = tail = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void add(Person x) {
		if (isEmpty()) {
			head = tail = new Node(x);
		}
		else {
			Node newNode = new Node(x);
			tail.setNext(newNode);
			tail = newNode;
		}
	}

	public void addMany(String[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			add(new Person(a[i], b[i]));
		}
	}

	public void traverse() {
		Node current = head;

		while (current != null) {
			System.out.println(current);
			current = current.getNext();
		}
	}

	public void sortByName() {
		MyList sortedList = new MyList();
		// Using selection sort
		while (!this.isEmpty()) {

			Node minNode = head;
			Node prevMinNode = null;
			Node nextMinNode = minNode.getNext();

			Node cur = head;
			Node prev = null;
			Node next = head.getNext();

			while (cur != null) {
//				System.out.println(cur.getData());
//				System.out.println(minNode.getData());
//				System.out.println(cur.getData().compareTo(minNode.getData()));
				if (cur.getData().compareTo(minNode.getData()) < 0) {
					minNode = cur;
					prevMinNode = prev;
					nextMinNode = next;
				}

				prev = cur;
				cur = next;
				next = (cur == null) ? null : cur.getNext();
			}

			// Insert the smallest to the tail of the sortedList
			sortedList.add(minNode.getData());

			// Remove the minNode from the list
			if (head == minNode) {
				if (tail == minNode) {
					head = tail = null;
				}
				head = nextMinNode;
			} else
			if (tail == minNode) {
				prevMinNode.setNext(null);
				tail = prevMinNode;
			}
			else {
				prevMinNode.setNext(nextMinNode);
			}
			
			minNode = null;
//			System.out.println("---------");
//			this.traverse();
		}

		this.setHead(sortedList.getHead());
		this.setTail(sortedList.getTail());
	}

}

class Node {

	private Person data;

	private Node next;

	public Node(Person data) {
		this.data = data;
		this.next = null;
	}

	public Node(Person data, Node next) {
		this(data);
		this.next = next;
	}

	public Person getData() {
		return data;
	}

	public void setData(Person data) {
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
		return this.getData().toString();
	}

}

class Person implements Comparable<Person> {

	String name;

	int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public int compareTo(Person t) {
		if (t == null)
			return 1;
		return this.name.compareTo(t.name);
	}

	@Override
	public String toString() {
		return name + " " + age;
	}

}
