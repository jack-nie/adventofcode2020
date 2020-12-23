package com.of.code.advent2020;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5_1 {
	public static void main(String[] args) {
		Path path;
		
		try {
			path = Paths.get(Day5_1.class.getResource("/day5.txt").toURI());
			@SuppressWarnings("resource")
			Stream<String> lines = Files.lines(path);

			Optional<Integer> l = lines.map(item -> {
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
			}).max((a, b) -> a.compareTo(b));

			System.out.println(l);
		} catch (IOException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
