package com.of.code.advent2020;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Day10 {
	public static void main(String[] args) {
		List<Long> list = Helper.generateLongListFromFile("/day10.txt");
		list.add(0L);
		Collections.sort(list);
		int result = part1(list);
		System.out.println(result);

		Long result2 = part2(list);
		System.out.println(result2);
	}

	public static int part1(List<Long> list) {
		HashMap<Long, Integer> hashMap = new HashMap<>();

		for (int i = 1; i < list.size(); i++) {
			long diff = list.get(i) - list.get(i - 1);
			hashMap.put(diff, hashMap.getOrDefault(diff, 0) + 1);
		}

		return hashMap.get(1L) * (hashMap.get(3L) + 1);
	}

	public static Long part2(List<Long> list) {
		HashMap<Long, Long> hashMap = new HashMap<>();
		hashMap.put((long) 0, 1L);
		List<Long> diffs = Arrays.asList(1L, 2L, 3L);

		for (int i = 1; i < list.size(); i++) {
			hashMap.put(list.get(i), 0L);
			for (Long diff : diffs) {
				if (hashMap.get(list.get(i) - diff) != null) {
					hashMap.put(list.get(i), hashMap.get(list.get(i)) + hashMap.get(list.get(i) - diff));
				}
			}

		}
		return hashMap.get(list.get(list.size() - 1));
	}

	public static Long[] convertLinesToList(Stream<String> lines) {
		return lines.map(Long::valueOf).toArray(Long[]::new);
	}

}
