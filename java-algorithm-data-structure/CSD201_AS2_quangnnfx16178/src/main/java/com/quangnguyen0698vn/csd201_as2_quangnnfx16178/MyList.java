
package com.quangnguyen0698vn.csd201_as2_quangnnfx16178;

public class MyList<T extends Comparable<T>> {

	
	private int size;


	private Node<T> head;


	private Node<T> tail;

	/**
	* assign newList to current list
	* @param newList the new list
	* assign head, tail and size of the newList to current list
	**/
	public void assign(MyList newList) {
		this.setHead(newList.head);
		this.setTail(newList.tail);
		this.setSize(newList.size);
	}

	/*
	* Default Constructor
	*/
	public MyList() {

	}

	public MyList(Node head, Node tail) {
		this.head = head;
		this.tail = tail;
	}


	public boolean isEmpty() {
		return head == null;
	}


	public void insertToHead(T item) {
		if (this.isEmpty()) {
			this.head = this.tail = new Node<>(item);
		} else {
			Node<T> newNode = new Node(item);
			newNode.setNext(this.head);
			this.setHead(newNode);
		}
		size++;
	}
	

	public void insertToTail(T item) {
		if (this.isEmpty()) {
			head = tail = new Node<>(item);
		} else {
			Node<T> newNode = new Node(item);
			tail.setNext(newNode);
			this.setTail(newNode);
		}
		size++;
	}


	public boolean deleteAtPosition(int pos) {
		if (pos >= size) return false;
		if (pos == 0) {
			if (this.head == this.tail) {
				this.head = this.tail = null;
			} else {
				this.head = this.head.getNext();
			}
			size--;
			return true;
		}
		Node<T> prev = null;
		Node<T> cur = this.getHead();
		for (int i = 0; i < pos; i++) {
			prev = cur;
			cur = cur.getNext();
		}
		if (cur == tail) {
			prev.setNext(null);
			tail = prev;
		} else
			prev.setNext(cur.getNext());
		size--;
		return true;
	}


	public void deleteTail() {
		if (head == tail) {
			this.size--;
			head = tail = null;
			return;
		}
		Node<T> prev = null;
		Node<T> cur = head;	
		while (cur != tail) {
			prev = cur;
			cur = cur.getNext();
		}
		prev.setNext(null);
		this.setTail(prev);
		this.size--;
	}

	public boolean deleteElement(T item) {
		Node<T> prev = null;
		Node<T> cur = head;
		while (cur != null) {
			if (cur.getInfo().compareTo(item) == 0) {
				if (cur == head) {
					if (cur == tail) {
						head = tail = null;
					}
					else head = head.getNext();
				} else 
				if (cur == tail) {
					prev.setNext(null);
					tail = prev;
				} else {
					prev.setNext(cur.getNext());
				}
				size--;
				return true;
			}
			prev = cur;
			cur = cur.getNext();
		}
		return false;
	}

	public void swap(Node<T> firstNode, Node<T> secondNode) {
		T temp = firstNode.getInfo();
		firstNode.setInfo(secondNode.getInfo());
		secondNode.setInfo(temp);
	}


	public void clear() {
		size = 0;
		head = tail = null;
	}

	public Node<T> getHead() {
		return head;
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
		Node<T> cur = this.getHead();
		while (cur != null) {
			MyLogger.log(cur.getInfo().toString()+"\n");
			cur = cur.getNext();
		}
	}
	
	/**
	 * return the combined liked list of two linked list a and b
	 * @param a the first linked list
	 * @param b the second linked list
	 * @return the combined linked list
	 * O(1)
	 */
	public MyList<T> join(MyList<T> a, MyList<T> b) {
		if (a.isEmpty()) return b;
		if (b.isEmpty()) return a;
		
		a.getTail().setNext(b.getHead());
		a.setTail(b.getTail());
		a.setSize(a.getSize() + b.getSize());
		b.clear();
		return a;
	}
	
	/**
	 * 
	 * @return true if the list is sorted, else false
	 * O(n)
	 */
	public boolean isSorted() {
		if (this.isEmpty() || this.getSize() == 1) return true;
		
		Node<T> prev = this.getHead();
		Node<T> cur = prev.getNext();
		
		while (cur != null) {
			if (prev.getInfo().compareTo(cur.getInfo()) > 0) return false;
			prev = cur;
			cur = cur.getNext();
		}
		
		return true;
	}
	
	/**
	 * below is my recursive quick-sort
	 * avg-case: O(n log n)
	 * worst-case: O(n^2)
	 * for ease of implementation, I always choose the rightmost element as the pivot
	 */
	public void sort() {
//		System.out.println("\nsort is called\n");
//		System.out.println("--------------");
//		this.traverse();
//		System.out.println("--------------");
		if (this.isSorted()) return; //O(n) or O(size of this list)
	
		//start partitioning
		MyList<T> leftPartition = new MyList<>();
		MyList<T> pivotList = new MyList<>();
		MyList<T> rightPartition = new MyList<>();
		
		T pivot = this.getTail().getInfo();
		Node<T> cur = this.getHead();
		
		while (cur != this.getTail()) {
			if (cur.getInfo().compareTo(pivot) < 0) {
				leftPartition.insertToTail(cur.getInfo());
			}
			else
			if (cur.getInfo().compareTo(pivot) > 0) {
				rightPartition.insertToTail(cur.getInfo());
			}
			else {
				pivotList.insertToTail(cur.getInfo());
			}
			cur = cur.getNext();
		}
		pivotList.insertToTail(cur.getInfo());
		//end-paritioning -> O(n) or O(size of this list)
		this.clear();
		//the depth of the recursion, or the stack max size is Log(N) in average case, and N in worst-case
		leftPartition.sort(); //Sort left partition
		rightPartition.sort(); //Sort right partition
		
//		Debugging purposes
//		System.out.println("\nleft-----------");
//		leftPartition.traverse();
//		System.out.println("\npivot----------");
//		pivotList.traverse();
//		System.out.println("\nright----------");
//		rightPartition.traverse();
		
		//Combine 3 list in O(1)
		MyList<T> sortedList = join(join(leftPartition, pivotList), rightPartition);
		
//		System.out.println("\njoin----------");
//		sortedList.traverse();
		//assign the sorted list to this list, O(1)
		this.assign(sortedList);
		//clean data
		leftPartition.clear();
		pivotList.clear();
		rightPartition.clear();
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
}
