package com.of.code.advent2020;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Day9 {
	public static void main(String[] args) {
		try {
			URI uri = Day9.class.getResource("/day9.txt").toURI();
			Stream<String> lines = Files.lines(Path.of(uri));
			Long[] nums = convertLinesToArray(lines);

			long part1 = part1(nums);
			
			long part2 = part2(nums, part1);

			System.out.println(part1);
			System.out.println(part2);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Long[] convertLinesToArray(Stream<String> lines) {
		return lines.map(Long::valueOf).toArray(Long[]::new);
	}

	public static Long part1(Long[] nums) {
		if (nums == null || nums.length < 5)
			return -1L;

		for (int i = 25; i < nums.length; i++) {
			Long target = nums[i];
			int begin = i - 25;
			int end = i - 1;
			if (!checkValid(nums, begin, end, target)) {
				return target;
			}
		}
		return -1L;
	}

	public static long part2(Long[] nums, Long target) {
		for (int i = 0; i < nums.length; i++) {
			Long sum = 0L;
			for (int j = i; j < nums.length; j++) {
				sum += nums[j];
				if (sum.equals(target)) {
					LongStream longStream = LongStream.range(i, j).map(x -> nums[(int) x]); 
					LongStream longStreamDup = LongStream.range(i, j).map(x -> nums[(int) x]);
					return longStream.max().orElse(0L) + longStreamDup.min().orElse(0L) ;
				}
			}
		}
		return -1L;
	}

	public static boolean checkValid(Long[] nums, int begin, int end, Long target) {
		HashMap<Long, Integer> hashMap = new HashMap<Long, Integer>();

		for (int i = begin; i <= end; i++) {
			if (hashMap.containsKey(target - nums[i])) {
				return true;
			}

			hashMap.put(nums[i], i);
		}

		return false;
	}

}
