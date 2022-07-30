package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {	
	
	public static <T extends Shape> void drawAll(List<?> shapes) {
		//for(T shape : shapes)
		//	shape.draw();
	}
	
	public static void print(List<?> list) {
		for(Object o : list)
			System.out.println(o);
	}
	
	public static void main(String[] args) {
		
		List<Rectangle> rectangles = new ArrayList<>();
		rectangles.add(new Rectangle());
		drawAll(rectangles);
	}
}
