package com.of.code.advent2020;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6_2 {
	public static void main(String[] args) {
		Path path;
		try {
			path = Paths.get(Day6_2.class.getResource("/day6.txt").toURI());
			String[] source = Files.readString(path).split("\n\n");

			int x = Arrays.stream(source).map(item -> commonCharsSize(item.split("\n"))).reduce((a, b) -> a + b).get();

			System.out.println(x);

		} catch (IOException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static int commonCharsSize(String[] strings) {
			List<String> result = new ArrayList<String>();
			int[] count = new int[26];
			for (int i=0; i<26; i++) {
				count[i] = Integer.MAX_VALUE;
			}
			for (String str : strings) {
				int[] tem = new int[26];
				for (int j=0; j<str.length(); j++) {
					tem[str.charAt(j)-'a']++;
				}
				for (int k=0; k<26; k++) {
					count[k] = Math.min(count[k], tem[k]);
				}
			}
			for (int i=0; i<26; i++) {
				while (count[i]-- > 0) {
					result.add((char)(i+'a')+"");
				}
			}
			return result.size();
	}

}
