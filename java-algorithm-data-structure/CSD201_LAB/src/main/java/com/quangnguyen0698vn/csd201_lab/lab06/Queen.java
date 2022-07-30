
package com.quangnguyen0698vn.csd201_lab.lab06;

import java.util.Scanner;

/**
 * @author quang
 * 
 * Bài Toán:  Hãy đặt n quân hậu trên bàn cờ nxn sao cho không có hai quân nào có thể tấn công lẫn nhau. Có thể dùng kỹ thuật quay lui để giải bài toán này như thế nào? Hãy viết chương trình để tìm các cách sắp xếp quân hậu trên bàn cờ (các nghiệm của bài toán )

Học viên tham khảo thêm tài liệu về bài toán con hậu tại đây

Gợi ý:

- Sau đây là gơi ý về Input, Output:

Input: 

 n = 5 ( 5 quân hậu sẽ được đặt vào bàn cờ gồm 5 hàng, 5 cột )

Output: Các cách sắp xếp 5 quân hậu trên bàn cờ ( các nghiệm) được tìm thấy, mỗi cách sắp xếp sẽ nằm trên 1 hàng. Hàng thứ k (cách sắp xếp k) có 5 giá trị, trong đó mỗi giá trị thứ i (i=1 đến 5)  của hàng k kí hiệu là  a[i,k] sẽ biểu diễn phần tử ở cột i và hàng a[i,k] trong bàn cờ.

Ví dụ với n=5 ta tìm được 10 cách sắp xếp dưới:

  1: 1   3   5   2   4

  2: 1   4   2   5   3

  3: 2   4   1   3   5

  4: 2   5   3   1   4

  5: 3   1   4   2   5

  6: 3   5   2   4   1

  7: 4   1   3   5   2

  8: 4   2   5   3   1

  9: 5   2   4   1   3

 10: 5   3   1   4   2

Trong đó hàng thứ (cách sắp xếp thứ) k=1, ta sẽ đặt các quân hậu tương ứng vào bàn cờ như sau:

Quân hậu 1: đặt vào cột 1 hàng 1

Quân hậu 2: đặt vào  cột 2 hàng 3

Quân hậu 3: đặt vào cột 3 hàng 5 

Quân hậu 4: đặt vào  cột 4 hàng 2

Quân hậu 5: đặt vào cột 5 hàng 4 

Tương tự với các hàng (cách sắp xếp) tiếp theo
 */
public class Queen {
	
	int n;
	int count;
	int position[];
	boolean col[], slope1[], slope2[];

	Queen(int n) {
		this.n = n;
		count = 0;
		position = new int[n+1];
		col = new boolean[n+1];
		slope1 = new boolean[(n+1)*2];
		slope2 = new boolean[(n+1)*2];
	}
	
	void backtrack(int i) {
		if (i==n+1) {
			count++;
			System.out.print(count + ": ");
			for (int j = 1; j <= n; j++)
				System.out.print(position[j] + " ");
			System.out.println();
			return;
		}
		for (int j = 1; j <= n; j++)
		if (!col[j] && !slope1[i-j+n] && !slope2[i+j]) {
			position[i] = j;
			col[j] = slope1[i-j+n] = slope2[i+j] = true;
			backtrack(i+1);
			col[j] = slope1[i-j+n] = slope2[i+j] = false;
			position[i] = 0;
		}
	}
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("n = ");
		int n = scanner.nextInt();
		Queen queen = new Queen(n);
		queen.backtrack(1);
	}
}
