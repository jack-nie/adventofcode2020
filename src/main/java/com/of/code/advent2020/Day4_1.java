package com.of.code.advent2020;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Day4_1 {
	public static void main(String[] args) {
		Path path;
		try {
			path = Paths.get(Day4_1.class.getResource("/day4.txt").toURI());
			String[] strArray = Files.readString(path).split("\r\n\r\n");
			long l = Arrays.stream(strArray).map(Day4_1::getCollect).filter(Day4_1::validPassport).count();
			System.out.println(l);
		} catch (IOException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private static Set<String> getCollect(String item) {
		return Arrays.stream(item.split("\r\n")).flatMap(strs -> Arrays.stream(strs.split(" ")))
				.map(str -> str.split(":")[0]).collect(Collectors.toSet());
	}

	public static boolean validPassport(Set<String> item) {
		return item.size() == 8 || (item.size() == 7 && !item.contains("cid"));
	}

}
