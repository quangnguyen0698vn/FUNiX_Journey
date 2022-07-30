package com.quangnguyen0698vn.csd201_lab.lab01;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author quang
 * Viết chương trình (sử dụng Java) nhập vào một mảng n các số nguyên, sau đó  hãy sắp xếp các phần tử của mảng theo thứ tự tăng dần bằng thuật toán nổi bọt (bubble sort đã được học ở bài 1) và liệt kê ra màn hình kết quả theo từng bước của thuật toán
 * 
 * Input:

n=7

a[]=5,7,11,3,9,2,6

Output:

 5  7  3  9  2  6  11

  5  3  7  2  6  9  11
  3  5  2  6  7  9  11
  3  2  5  6  7  9  11
  2  3  5  6  7  9  11
  2  3  5  6  7  9  11
 */
public class Lab01App {
    
    public static void display(int[] arr) {
        for (int x : arr)
            System.out.print(x + " ");
        System.out.println();
    }
    
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j+1 < arr.length; j++)
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                    flag = true;
                }
            display(arr);
            if (!flag) break;
        }
    }
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("n=");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        scanner.nextLine();
        System.out.print("a[]=");
        StringTokenizer st = new StringTokenizer(scanner.nextLine(),",");
        for(int i = 0; i < n; i++) {
            if (st.hasMoreTokens()) arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        bubbleSort(sortedArr);
        
//        for (int x : arr) System.out.print(x + " ");
    }
    
}