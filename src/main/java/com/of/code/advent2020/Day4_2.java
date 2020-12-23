package com.of.code.advent2020;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Day4_2 {
	public static void main(String[] args) {
		Path path;
		try {
			path = Paths.get(Day4_2.class.getResource("/day4.txt").toURI());
			String[] strArray = Files.readString(path).split("\r\n\r\n");
			long l = Arrays.stream(strArray).map(item -> {

				Map<String, String> map = Arrays.stream(item.split("\r\n"))
						.flatMap(strs -> Arrays.stream(strs.split(" "))).map(str -> str.split(":"))
						.collect(Collectors.toMap(array -> array[0], array -> array[1]));

				return map;

			}).
					filter(item -> Day4_1.validPassport(item.keySet().stream().collect(Collectors.toSet()))).
					filter(item -> item.entrySet().stream().allMatch(map -> checkValidValue(map.getKey(), map.getValue()))).
					count();
			System.out.println(l);
		} catch (IOException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private static boolean checkInRange(String value, int begin, int end) {
		int number = Integer.parseInt(value);
		if (number >= begin && number <= end) {
			return true;
		}
		return false;
	}

	private static boolean checkValidValue(String key, String value) {
		boolean flag = false;

		switch (key) {
		case "byr":
			flag = value.matches("^\\d{4}$") && checkInRange(value, 1920, 2002);
			break;
		case "iyr":
			flag = value.matches("^\\d{4}$") && checkInRange(value, 2010, 2020);
			break;
		case "eyr":
			flag = value.matches("^\\d{4}$") && checkInRange(value, 2020, 2030);
			break;
		case "hgt":
			flag = value.matches("^\\d+cm$") && checkInRange(value.split("cm")[0], 150, 193)
					|| value.matches("^\\d+in$") && checkInRange(value.split("in")[0], 59, 76);
			break;
		case "hcl":
			flag = value.matches("^#[0-9a-f]{6}$");
			break;
		case "ecl":
			flag = value.matches("^amb|blu|brn|gry|grn|hzl|oth$");
			break;
		case "pid":
			flag = value.matches("^\\d{9}$");
			break;
		case "cid":
			flag = true;
			break;
		default:
			break;
		}

		return flag;

	}

}