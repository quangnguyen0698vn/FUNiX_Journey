
package com.quangnguyen0698vn.csd201_as2_quangnnfx16178;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * This class manages all functions relate to the product
 * 
 */

public class OperationToProduct {

	public Scanner scanner = new Scanner(System.in);

	/**
	 * Show the prompt and ask user to choose option
	 * @return the option that user input
	 */
	public int optionFromUser() {
		System.out.print("Choice=");
		int ret = scanner.nextInt();
		scanner.nextLine();
		return ret;
	}

	/**
	 * 
	 * Searching and returning the index of product p in the list. If not found
	 * 
	 * return -1.
	 * @param p Product for searching
	 * @param list The Linked List
	 * @return The index of product p in the list I used the linear search algorithm O(n)
	 */

	public int index(Product p, MyList<Product> list) {
		Node<Product> cur = list.getHead();
		int ret = -1;
		int idx = 0;
		while (cur != null) {
			if (cur.getInfo().compareTo(p) == 0) {
				ret = idx;
				break;
			}
			idx++;
			cur = cur.getNext();
		}
		return ret;
	}

	/**
	 * 
	 * Creating and returning a product with info input from keyboard.
	 * @return The product
	 * 
	 */

	public Product createProduct() {
		System.out.print("\nInput new ID:");
		String pid = scanner.nextLine();
		System.out.print("Input Product's Name:");
		String pname = scanner.nextLine();
		System.out.print("Input Product's quantity:");
		int quantity = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Input Product's price:");
		double price = scanner.nextDouble();
		scanner.nextLine();
		return new Product(pid, pname, quantity, price);
	}

	/**
	 * read next Product in the file
	 * @param scanner the scanner of the file
	 * @return null if error, else the next Product in file
	 */
	
	private Product readNextProduct(Scanner scanner) {
		// For safety purpose
		if (!scanner.hasNextLine())
			return null;
		String pid = scanner.nextLine();

		// For safety purpose
		if (!scanner.hasNextLine())
			return null;

		String pname = scanner.nextLine();

		// For safety purpose
		if (!scanner.hasNextInt())
			return null;

		int quantity = scanner.nextInt();
		scanner.nextLine();

		// For safety purpose
		if (!scanner.hasNextDouble())
			return null;

		double price = scanner.nextDouble();
		scanner.nextLine();

		Product newProduct = new Product(pid, pname, quantity, price);
		return newProduct;
	}

	/**
	 * 
	 * Reading all products from the file and insert them to the list at tail.
	 * @param fileName The file name of the file
	 * @param list The Linked List contains all products that read from file
	 * 
	 */

	public void getAllItemsFromFile(String fileName, MyList<Product> list) {
		try (Scanner scanner = new Scanner(new File(fileName))) {
			while (scanner.hasNextLine()) {	
				Product newProduct = readNextProduct(scanner);
				if (newProduct == null) break;
				// System.out.println(newProduct);
				list.insertToTail(newProduct);
			}

			MyLogger.log("\nGet all items from " + fileName + " and add to the tail of the list sucessfully!\n");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Reading all products from the file and insert them to the stack.
	 * @param fileName The file name of the file
	 * @param stack The Stack contains all products that read from file
	 * 
	 */

	public void getAllItemsFromFile(String fileName, MyStack<Product> stack) {
		try (Scanner scanner = new Scanner(new File(fileName))) {
			while (scanner.hasNextLine()) {
				Product newProduct = readNextProduct(scanner);
				if (newProduct == null) break;
				stack.push(newProduct);
			}
			MyLogger.log("\nGet all items from " + fileName + " and push to the stack sucessfully!\n");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Reading all products from the file and insert them to the queue.
	 * @param fileName The file name of the file
	 * @param queue The Queue contains all products that read from file
	 * 
	 */

	public void getAllItemsFromFile(String fileName, MyQueue<Product> queue) {
		try (Scanner scanner = new Scanner(new File(fileName))) {
			while (scanner.hasNextLine()) {
				Product newProduct = readNextProduct(scanner);
				if (newProduct == null) break;
				queue.add(newProduct);
			}
			MyLogger.log("\nGet all items from " + fileName + " and add to the queue successfully!\n");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Adding a product to the list, info of the product input from keyboard.
	 * @param list The Linked list
	 * 
	 */

	public void addLast(MyList<Product> list) {
		list.insertToTail(createProduct());
	}

	private void displayHeader() {
		MyLogger.log("\n|" + MyLogger.paddingLeft("ID", 15) + "|" + MyLogger.paddingLeft("Title", 30) + "|"
				+ MyLogger.paddingLeft("Quantity", 15) + "|" + MyLogger.paddingLeft("Price", 15) + "|\n");
		MyLogger.log("|" + MyLogger.dash(15 + 30 + 15 + 15 + 5 - 2) + "|\n");
	}

	/**
	 * 
	 * Printing all product of the list to console screen
	 * @param list
	 * 
	 */

	public void displayAll(MyList<Product> list) {
		displayHeader();
		list.traverse();
		MyLogger.log("|" + MyLogger.dash(15 + 30 + 15 + 15 + 5 - 2) + "|\n");
		MyLogger.log("\nShow all products in the list successfully!\n");
	}

	/**
	 * 
	 * Printing all product of the stack to console screen
	 * @param list
	 * 
	 */

	public void displayAll(MyStack<Product> stack) {
		displayHeader();
		stack.traverse();
		MyLogger.log("\nShow all products in the stack successfully!\n");
	}

	/**
	 * 
	 * Printing all product of the queue to console screen
	 * @param list
	 * 
	 */

	public void displayAll(MyQueue<Product> queue) {
		displayHeader();
		queue.traverse();
		MyLogger.log("\nShow all products in the queue successfully!\n");
	}

	/**
	 * 
	 * Writing all products from the list to the file
	 * @param fileName Input file name
	 * @param list Input Linked list
	 * 
	 */

	public void writeAllItemsToFile(String fileName, MyList<Product> list) {
		try (FileWriter fileWriter = new FileWriter(new File(fileName))) {
			Node<Product> cur = list.getHead();
			while (cur != null) {
				fileWriter.write(cur.getInfo().getBcode() + "\n");
				fileWriter.write(cur.getInfo().getTitle() + "\n");
				fileWriter.write(cur.getInfo().getQuantity() + "\n");
				fileWriter.write(cur.getInfo().getPrice() + "\n");
				cur = cur.getNext();
			}

			MyLogger.log("\nSave all the products to the " + fileName + " file sucessfully!\n");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Searching product by ID input from keyboard.
	 * @param list
	 * 
	 */

	public void searchByCode(MyList<Product> list) {
		System.out.print("\nInput the ID to search=");
		String searchID = scanner.nextLine();
		Node<Product> cur = list.getHead();
		Node<Product> result = null;
		while (cur != null) {
			if (cur.getInfo().getBcode().equals(searchID)) {
				result = cur;
				break;
			}
			cur = cur.getNext();
		}
		MyLogger.log("\nResult: ");
		if (result == null)
			MyLogger.log("-1\n");
		// System.out.println("Not found in the list");
		else
			MyLogger.log(result.getInfo() + "\n");
	}

	/**
	 * 
	 * Deleting first product that has the ID input from keyboard from the list.
	 * @param list
	 * 
	 */

	public void deleteByCode(MyList<Product> list) {
		System.out.print("\nInput the bcode to delete=");
		String searchID = scanner.nextLine();
		if (list.deleteElement(new Product(searchID, null, -1, 0))) {
			MyLogger.log("\nDeleted!\n");
		}
		else
			MyLogger.log("\nThe list does not contain the item that needs deleting!\n");
	}

	/**
	 * 
	 * Sorting products in linked list by ID
	 * @param list The Linked list
	 * 
	 */

	public void sortByCode(MyList<Product> list) {
		list.sort();
		MyLogger.log("\nSort the product list by product id successfully!\n");
	}

	/**
	 * 
	 * Adding new product to head of Linked List. The info input from keyboard.
	 * @param list The linked list
	 * 
	 */

	public void addFirst(MyList<Product> list) {
		list.insertToHead(createProduct());
	}

	/**
	 * 
	 * Convert a decimal to a binary number. Example: input i = 18 -> Output = 10010
	 * @param i Input decimal number
	 * @return a binary numbers
	 * 
	 */

	public String convertToBinary(int n) {
		if (n == 0)
			return "0";
		if (n == 1)
			return "1";
		return convertToBinary(n / 2) + ((n % 2 == 1) ? "1" : "0");
	}

	/**
	 * 
	 * Deleting the product at position
	 * @param list The Linked List
	 * @param pos The position of product to be deleted
	 * 
	 */

	public void deleteAtPosition(MyList<Product> list, int pos) {
		list.deleteAtPosition(pos);
	}

	void convertTheFirstQuantityToBinary(MyList<Product> products) {
		if (products.isEmpty()) {
			MyLogger.log("\nThere are no products in the list\n");
			return;
		}
		int quantity = products.getHead().getInfo().getQuantity();
		MyLogger.log("\nQuantity=" + quantity + "=>(" + convertToBinary(quantity) + ")\n");
	}

}