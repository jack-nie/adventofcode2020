package com.of.code.advent2020;

import java.util.Collections;
import java.util.List;

public class Day11 {
	public static void main(String[] args) {
		List<Long> list = Helper.generateLongListFromFile("/day10.txt");
		list.add(0L);
		Collections.sort(list);
	}

}
