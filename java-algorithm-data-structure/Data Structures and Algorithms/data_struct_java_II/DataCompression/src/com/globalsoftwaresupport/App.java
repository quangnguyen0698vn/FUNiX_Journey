package com.globalsoftwaresupport;

public class App {

	public static void main(String[] args) {
		
		RunLengthEncoding rle = new RunLengthEncoding();
		
		String encoded = rle.encoding("AAAAAAAAAAABCDDEEEZZZZ");
		System.out.println(encoded);
		
		System.out.println(rle.decoding(encoded));
	}
}
