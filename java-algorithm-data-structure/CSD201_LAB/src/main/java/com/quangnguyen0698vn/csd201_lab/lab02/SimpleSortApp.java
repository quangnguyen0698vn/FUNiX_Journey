package com.quangnguyen0698vn.csd201_lab.lab02;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author quang
 Sử dụng ngôn ngữ lập trình java, hãy tạo một lớp có tên là SimpleSortApp, hàm khởi tạo để  nhận giá trị cho mảng a và biến n là số phần tử của mảng, cài đặt các phương thức :

1. selectSort() : Sắp xếp bằng phương pháp lựa chọn

2. insertSort(): Sắp xếp bằng phương pháp chèn

3. Search (int value) trả về số nguyên là vị trí đầu tiên của value trong mảng a sử dụng thuật toán tìm kiếm tuyến tính 

Gợi ý:

- Sau đây là yêu cầu input, output 

Input: n=7;  a[]={5,7,11,3,9,2,6}; value=11

Output:

1. selectSort():   2  3  5  6  7  9  11

2. insertSort():   2  3  5  6  7  9  11

3.Search (11) : 2


 */

class SimpleSort {
    private int originalArr[];
    private int sortedArr[];
    
    public SimpleSort(int a[]) {
        this.originalArr = Arrays.copyOf(a, a.length);
    }
    
    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    //https://www.geeksforgeeks.org/selection-sort/
    public void selectSort(int[] a) {
        int n = a.length;
        this.sortedArr = Arrays.copyOf(a, n);
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++)
                if (this.sortedArr[minIndex] > this.sortedArr[j]) minIndex = j;
            swap(this.sortedArr, i, minIndex);
        }
        
        System.out.println("Selection sort's result: ");
        display(this.sortedArr);
    }
    
    //https://www.geeksforgeeks.org/insertion-sort/
    public void insertSort(int[] a) {
        int n = a.length;
        this.sortedArr = Arrays.copyOf(a, n);
        for (int i = 0; i < n; i++) {
            int key = this.sortedArr[i];
            int insertPosition = i;
            for (int j=0; j<i; j++) 
                if (key < this.sortedArr[j]) {
                    insertPosition = j;
                    break;
                }
            
            for(int j = i; j > insertPosition; j--) 
                this.sortedArr[j] = this.sortedArr[j-1];
            this.sortedArr[insertPosition] = key;
        }
        System.out.println("Insertion sort's result: ");

        display(this.sortedArr);
    }
    
    //https://www.geeksforgeeks.org/linear-search/
    public int search(int a[], int value) {
        int ret = -1;
        for(int i = 0; i < a.length; i++)
            if (a[i] == value) {
                ret = i;
                break;
            }
        return ret;
    }

    public void display(int[] a) {
        for(int x : a) System.out.print(x + " ");
        System.out.println();
    }
}

public class SimpleSortApp {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[] a = {
                5,
                7,
                11,
                3,
                9,
                2,
                6
            };
        
        SimpleSort sort = new SimpleSort(a);
        
        while (true) {
                System.out.println("\n Choose your option:");
                System.out.println("  1. Display data");
                System.out.println("  2. Selection sort");
                System.out.println("  3. Insertion sort");
                System.out.println("  4. Search");
                System.out.println("  0. Exit\n");
                System.out.print("  Your selection (0 -> 4): ");
                int choice = scanner.nextInt();
                if (choice == 0) {
                    System.out.println(" Good bye, have a nice day!");
                    break;
                }
                switch (choice) {
                    case 1:
                        sort.display(a);
                        break;
                    case 2:
                        sort.selectSort(a);
                        break;
                    case 3:
                        sort.insertSort(a);
                        break;
                    case 4:
                        System.out.println("Search for value " + 11 + " result: " + sort.search(a, 11) + " ");
                        break;
                    default:
                        System.out.println("**Invalid choice. Try again.**");
                }
            }
        }
}
