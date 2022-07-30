package com.globalsoftwaresupport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLengthEncoding {

	public String encoding(String text) {
		
		// String class concatenation operation is rather inefficient
		StringBuilder stringBuilder = new StringBuilder();
		
		// consider all the letters in the text
		for(int i=0;i<text.length();++i) {
			
			// we assume that there are no adjacent same letters
			int runLength = 1;
			
			while(i+1 < text.length() && text.charAt(i) == text.charAt(i+1)) {
				runLength++;
				i++;
			}
			
			// we have to append the numerical value + character
			stringBuilder.append(runLength);
			stringBuilder.append(text.charAt(i));
		}
		
		return stringBuilder.toString();
	}
	
	public String decoding(String text) {
		
		// String class concatenation operation is rather inefficient
		StringBuilder stringBuilder = new StringBuilder();
		
		// regular expressions (substring search)
		Pattern pattern = Pattern.compile("[0-9]+|[a-zA-Z]");
		Matcher matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			int num = Integer.parseInt(matcher.group());
			matcher.find();
			while( num-- != 0 ){
				stringBuilder.append(matcher.group());
			}
		}
		
		return stringBuilder.toString();
	}
}



