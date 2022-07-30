package com.quangnguyen0698vn.csd201_lab.lab03;

/**
 * @author quang 
 * Viết một chương trình để tạo ra một danh sách liên kết đơn mà trong đó dữ
 * liệu của các node là số nguyên, khi thêm một phần tử thì thêm vào cuối của danh sách.
 * Hãy liệt kê ra tất cả các phần tử trong danh sách, sau đó hãy tìm chỉ số của phần tử
 * đầu tiên có giá trị >x (x là 1 số nguyên)
 * 
 * Gợi ý:
 * 
 * - Sau đây là gợi ý về input và output
 * 
 * Input: a[]={7,2,9,8,6,3}; x=8
 * 
 * Output:
 * 
 * Traverse: 7 2 9 8 6 3
 * 
 * Search(8): 2 (có nghĩa là tìm vị trí của giá trị đầu tiên trong mảng mà lớn hơn giá trị
 * 8. Hiện tại trên danh sách liên kết giá trị lớn hơn 8 đầu tiên là 9 đang nằm ở vị trí 2
 * trên danh sách liên kết)
 */
public class Lab03App {

	public static void main(String[] args) {
		MyList t = new MyList();
		int[] a = { 7, 2, 9, 8, 6, 3 };
		t.addMany(a);
		System.out.println("\n Traverse:");
		t.traverse();
		System.out.println("\n Search:");
		t.search(8);
		System.out.println();
	}

}

class MyList {

	Node head, tail;

	public MyList() {
		head = tail = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void clear() {
		head = tail = null;
	}

	public void addTail(int x) {
		if (tail == null) {
			head = tail = new Node(x);
		}
		else {
			Node newNode = new Node(x);
			tail.setNext(newNode);
			tail = newNode;
		}
	}

	public void addMany(int[] arr) {
		for (int x : arr)
			addTail(x);
	}

	public void visit(Node p) {
		System.out.print(p.getData() + " ");
	}

	public void traverse() {
		Node current = head;
		while (current != null) {
			visit(current);
			current = current.getNext();
		}
		System.out.println();
	}

	public void search(int x) {
		int ret = -1;
		int idx = 0;
		Node current = head;
		while (current != null) {
			if (current.getData() > x) {
				ret = idx;
				break;
			}
			current = current.getNext();
			idx++;
		}
		System.out.println(ret);
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
		return "Node{" + "data=" + data + '}';
	}

}