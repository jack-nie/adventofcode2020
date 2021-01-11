package com.of.code.advent2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1_2 {

	public static void main(String[] args) {
		List<Integer> nums = Helper.generateIntArrayFromFile("/day1.txt");
		if (nums == null || nums.size() <= 2) {
			return;
		}
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Collections.sort(nums);
		sumThreeNum(result, nums, new ArrayList<>(), 2020, 0);
		if (result.size() != 0) {
			System.out.println(result.get(0).stream().reduce((a, b) -> a * b).get());
		}
		System.out.println(result);
	}

	public static void sumThreeNum(List<List<Integer>> result, List<Integer> nums, List<Integer> list, int target,
			int index) {

		if (list.size() == 3) {
			if (target == 0) {
				List<Integer> tempList = new ArrayList<Integer>();
				tempList.addAll(list);
				result.add(tempList);
			}
			return;
		}

		for (int i = index; i < nums.size(); i++) {
			list.add(nums.get(i));
			sumThreeNum(result, nums, list, target - nums.get(i), i);
			list.remove(nums.get(i));
		}

	}

}
