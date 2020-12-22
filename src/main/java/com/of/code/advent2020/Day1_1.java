package com.of.code.advent2020;

import java.util.HashMap;
import java.util.List;

public class Day1_1 {
	public static void main(String[] args) {
		List<Integer> list = Helper.generateIntArrayFromFile("/day1.txt");

		int[] result = sumTwoNum(list.toArray(new Integer[list.size()]), 2020);

		System.out.println(result[0] * result[1]);

	}

	public static int[] sumTwoNum(Integer[] nums, int target) {
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			if (hashMap.containsKey(target - nums[i])) {
				result[0] = nums[i];
				result[1] = target - nums[i];
				break;
			}

			hashMap.put(nums[i], i);
		}
		return result;
	}

}
