package com.of.code.advent2020;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day3_1 {
	public static void main(String[] args) {
	    Path path;
		try {
			path = Paths.get(Day3_1.class.getResource("/day3.txt").toURI());
			int count = countTrees(path, 3, 1);
			System.out.println(count);
			
			 long solution = countTrees(path, 1, 1);
		        solution *= countTrees(path, 3, 1);
		        solution *= countTrees(path, 5, 1);
		        solution *= countTrees(path, 7, 1);
		        solution *= countTrees(path, 1, 2);

		        System.out.println("Part II: " + solution);

		} catch (IOException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public static int countTrees(Path path, int stepsRight, int stepsDown) throws IOException {
		char[][] map = Files.
				lines(path).
				map(String::toCharArray).
				toArray(char[][]::new);
	
		int right = 0;
        int down = 0;
        int mapWidth = map[0].length;

        int treeCount = 0;
        while (down < map.length) {
            if (map[down][right] == '#') {
                treeCount++;
            }
            right = (right + stepsRight) % mapWidth; // <1>
            down += stepsDown;
        }
        return treeCount;
	}
}
