package com.of.code.advent2020;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6_1 {
	public static void main(String[] args) {
		Path path;
		try {
			path = Paths.get(Day6_1.class.getResource("/day6.txt").toURI());
			String[] source = Files.readString(path).split("\n\n");

			int x = Stream.of(source).map(item ->
			Arrays.
						asList(item.split("\n")).
						stream().
						flatMap(a -> a.chars().mapToObj(c -> (char)c).collect(Collectors.toSet()).stream()).
						collect(Collectors.toSet()).size()
			).
					reduce((a, b) -> a + b).
					get();

			System.out.println(x);

		} catch (IOException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
