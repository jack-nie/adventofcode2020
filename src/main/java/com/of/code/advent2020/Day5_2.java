package com.of.code.advent2020;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5_2 {
	public static void main(String[] args) {
		Path path;
		
		try {
			path = Paths.get(Day5_2.class.getResource("/day5.txt").toURI());
			@SuppressWarnings("resource")
			Stream<String> lines = Files.lines(path);

			Set<Integer> collect = getIntegers(lines).collect(Collectors.toSet());

			Integer max = collect.stream().max(Integer::compareTo).get();
			Integer min = collect.stream().min(Integer::compareTo).get();

			for (int i=min; i<max; i++) {
				if (!collect.contains(i) && collect.contains(i+1) && collect.contains(i-1)) {
					System.out.println(i);
				}
			}
		} catch (IOException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static Stream<Integer> getIntegers(Stream<String> lines) {
		return lines.map(item -> {
					int rows = 0;
					int left = 0;
					int right = 127;
					for (int i = 0; i < 7; i++) {
						if (i == 6) {
							if (item.charAt(i) == 'B') {
								rows = right;
							}
							if (item.charAt(i) == 'F') {
								rows = left;
							}
						}
						if (item.charAt(i) == 'B') {
							left = (right + left) / 2 + 1;
						}
						if (item.charAt(i) == 'F') {
							right = (right + left) / 2;
						}
					}

					int cols = 0;
					int colLeft = 0;
					int colRight = 7;
					for (int i = 7; i < 10; i++) {
						if (i == 9) {
							if (item.charAt(i) == 'L') {
								cols = colLeft;
							}
							if (item.charAt(i) == 'R') {
								cols = colRight;
							}
						}
						if (item.charAt(i) == 'L') {
							colRight = (colRight + colLeft) / 2;
						}
						if (item.charAt(i) == 'R') {
							colLeft = (colRight + colLeft) / 2 + 1;
						}

					}
					return rows * 8 + cols;
				});
	}

}
