package com.of.code.advent2020;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day13 {
	public static void main(String[] args) {

		List<String> lines = Helper.generateStringArrayFromFile("/day13.txt");
		long part1 = part1(lines);
		System.out.println(part1);

	}

	public static long part1(List<String> lines) {
		long timestamp = Long.valueOf(lines.get(0));
		List<Long> list = Stream.of(lines.get(1).split(",")).filter(Day13::isInteger).map(Long::valueOf)
				.collect(Collectors.toList());
		long tmp = -1;
		long time = -1;
		outer: for (long i = timestamp;; i++) {
			for (Long car : list) {
				if (i % car == 0) {
					tmp = car;
					time = i;
					break outer;
				}
			}
		}
		return (time - timestamp) * tmp;
	}

	public static boolean isInteger(String str) {

		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

		return pattern.matcher(str).matches();

	}

}
