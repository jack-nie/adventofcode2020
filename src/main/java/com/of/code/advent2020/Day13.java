package com.of.code.advent2020;

import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day13 {
	public static void main(String[] args) {

		List<String> lines = Helper.generateStringArrayFromFile("/day13.txt");
		long part1 = part1(lines);
		System.out.println(part1);
		
		long part2 = part2(lines);
		System.out.println(part2);

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

	public static long part2(List<String> lines) {
		String[] s = lines.get(1).split(",");
		long[][] nums = range(0, s.length).filter(i -> !s[i].equals("x"))
				.mapToObj(i -> new long[] { parseLong(s[i]), i }).toArray(long[][]::new);
		long product = stream(nums).mapToLong(a -> a[0]).reduce((a, b) -> a * b).getAsLong();
		long sum = stream(nums).mapToLong(a -> a[1] * (product / a[0]) * inverseModulo(product / a[0], a[0])).sum();
		return product - sum % product;
	}

	public static long inverseModulo(long x, long y) {
		if (x != 0) {
			long modulo = y % x;
			return modulo == 0 ? 1 : y - inverseModulo(modulo, x) * y / x;
		}
		return 0;
	}

}
