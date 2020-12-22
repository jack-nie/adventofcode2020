package com.of.code.advent2020;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Day2_1 {
	public static void main(String[] args) {
		List<String> strList = Helper.generateStringArrayFromFile("/day2.txt");
		int count = 0;
		for (String item:strList) {
			String[] strArray = StringUtils.split(item, " :-");
			for (int i = 0; i < strArray.length; i++) {
				int small = Integer.parseInt(strArray[0]);
				int big = Integer.parseInt(strArray[1]);
				String match = strArray[2];
				String source = strArray[3];
				
				char[] charArray = source.toCharArray();
				int innerCount = 0;
				for (int j = 0; j < charArray.length; j++) {
					if (match.equals(String.valueOf(charArray[j]))) {
						innerCount++;
					}
				}
				if (innerCount >= small && innerCount <= big) {
					count++;
				}
			}
		};
		System.out.println(count);
	}
}
