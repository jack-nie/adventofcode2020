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

			Optional<Integer> l = Day5_2.getIntegers(lines).max(Integer::compareTo);

			System.out.println(l);
		} catch (IOException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
