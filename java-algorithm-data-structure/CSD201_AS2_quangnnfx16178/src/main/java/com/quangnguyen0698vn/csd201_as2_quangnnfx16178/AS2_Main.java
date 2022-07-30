
package com.quangnguyen0698vn.csd201_as2_quangnnfx16178;

/**
 * @author quang
 */
/**
 * Viết chương trình quản lý sản phẩm trong kho hàng của một siêu thị, biết rằng mỗi sản phẩm gồm có các thuộc tính: Mã sản phẩm, tên sản phẩm, đơn giá, số lượng.

 Chương trình gồm các chức năng sau

Đọc dữ liệu có sẵn từ file chứa các sản phẩm rồi lưu vào Linked List
Nhập và thêm một sản phẩm vào cuối của danh sách Linked List
Hiển thị thông tin của các sản phẩm trong Linked List
Lưu danh sách các sản phẩm vào file
Tìm kiếm thông tin của sản phẩm theo ID
Xóa sản phẩm trong danh sách theo ID
Sắp xếp các sản phẩm  trong danh sách theo ID
Biểu diễn số lượng sản phẩm (đang ở hệ đếm cơ số 10) của phần tử đầu tiên trong Linked List về hệ đếm nhị phân bằng phương pháp đệ quy.
Đọc dữ liệu từ file chứa các sản phẩm rồi lưu vào stack. Liệt kê ra màn hình các sản phẩm có trong stack.
Đọc dữ liệu từ file chứa các sản phẩm lưu vào queue. Hiển thị ra màn hình các sản phẩm có trong queue.
 * 
 */
public class AS2_Main {

	public static void showMenu() {

		System.out.println("\nChoose one of this options:");
		System.out.println("Product list:");
		System.out.println("1. Load data from file and display");
		System.out.println("2. Input & add to the end.");
		System.out.println("3. Display data");
		System.out.println("4. Save product list to file.");
		System.out.println("5. Search by ID");
		System.out.println("6. Delete by ID");
		System.out.println("7. Sort by ID.");
		System.out.println("8. Convert to Binary");
		System.out.println("9. Load to stack and display");
		System.out.println("10. Load to queue and display.");
		System.out.println("0. Exit\n");

	}

	public static void main(String[] args) {
		OperationToProduct operationToProduct = new OperationToProduct();
		int option = -1;
		MyList<Product> products = new MyList<>();
		MyStack<Product> stackOfProducts = new MyStack<>();
		MyQueue<Product> queueOfProducts = new MyQueue<>();
		MyLogger.clearFile();
		
		while (option != 0) {
			showMenu();
			option = operationToProduct.optionFromUser();
			if (option == 0) break;
			switch (option) {
				case 1:
					products.clear();
					operationToProduct.getAllItemsFromFile("data.txt", products);
					operationToProduct.displayAll(products);
					break;	
				case 2:
					operationToProduct.addLast(products);
					break;
				case 3:
					operationToProduct.displayAll(products);
					break;
				case 4:
					operationToProduct.writeAllItemsToFile("data.txt", products);
					break;
				case 5:
					operationToProduct.searchByCode(products);
					break;
				case 6:
					operationToProduct.deleteByCode(products);
					break;
				case 7:
					operationToProduct.sortByCode(products);
					break;
				case 8:
					operationToProduct.convertTheFirstQuantityToBinary(products);
					break;
				case 9:
					operationToProduct.getAllItemsFromFile("data.txt", stackOfProducts);
					operationToProduct.displayAll(stackOfProducts);
					break;
				case 10:
					operationToProduct.getAllItemsFromFile("data.txt", queueOfProducts);
					operationToProduct.displayAll(queueOfProducts);
					break;
				default:
					break;
			}
		}
	}

}
