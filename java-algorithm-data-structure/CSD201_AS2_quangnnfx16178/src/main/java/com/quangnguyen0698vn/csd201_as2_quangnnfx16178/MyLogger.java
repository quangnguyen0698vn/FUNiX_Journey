
package com.quangnguyen0698vn.csd201_as2_quangnnfx16178;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author quang
 */
public class MyLogger {
	public static void logConsole(String s) {
		System.out.print(s);
	}
	
	public static void logFile(String s) {
		try (FileWriter fileWriter = new FileWriter(new File("console_output.txt"), true)) {
			fileWriter.write(s);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void log(String s) {
		logConsole(s);
		logFile(s);
	}
	
	public static void clearFile() {
		try (FileWriter fileWriter = new FileWriter(new File("console_output.txt"))) {
			fileWriter.write("");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String paddingLeft(String s, int n) {
		String ret = s;
		while (ret.length() < n)
			ret = ret + " ";
		return ret;
	}
	
	public static String paddingRight(String s, int n) {
		String ret = s;
		while (ret.length() < n)
			ret = " " + ret;
		return ret;
	}
	
	public static String dash(int n) {
		String ret = "";
		for (int i = 0; i < n; i++) 
			ret = ret + "-";
		return ret;
	}
}
