package com.of.code.advent2020;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day11 {
	public static void main(String[] args) {
		List<String> list = Helper.generateStringArrayFromFile("/day11.txt");
		char[][] grid = Helper.parseGrid(list);
        long result = part1(grid);
        System.out.println(result);
        
        long result2 = part2(grid);
        System.out.println(result2);
	}
	
	public static long part1(char[][] grid)  {
		char[][] input = grid;
		int changes;
		do{
			changes = 0;
			char[][] newGrid = new char[input.length][input[0].length];
			for(int i = 0; i<input.length; i++){
				for(int j = 0; j<input[0].length; j++){
					Point p = new Point(i, j);
					char[][] finalInput = input;
					long occupied = Arrays.stream(Direction.values()).filter(d ->
							d.getInGrid(finalInput, d.move(p)) == '#'
					).count();
					changes = getChanges(input, changes, newGrid, i, j, occupied, 4L);
				}
			}
			input = newGrid;
		} while(changes > 0);
		return getCount(input, '#');
	}
	
	private static long getCount(char[][] grid, char ch) {
		return Stream.of(grid).flatMapToInt(c -> new String(c).chars()).filter(c -> c == ch).count();
	}

	private static int getChanges(char[][] input, int changes, char[][] newGrid, int i, int j, long occupied, long numOccupied) {
		if(occupied == 0L && input[i][j] == 'L'){
			newGrid[i][j] = '#';
			changes++;
		} else if(occupied >= numOccupied && input[i][j] == '#'){
			newGrid[i][j] = 'L';
			changes++;
		} else {
			newGrid[i][j] = input[i][j];
		}
		return changes;
	}
	
	public static long part2(char[][] grid)  {
		char[][] input = grid;
		int changes;
		do{
			changes = 0;
			char[][] newGrid = new char[input.length][input[0].length];
			for(int i = 0; i<input.length; i++){
				for(int j = 0; j<input[0].length; j++){
					int occupied = countOccupied(input, i, j);
					changes = getChanges(input, changes, newGrid, i, j, occupied, 5L);
				}
			}
			input = newGrid;
		} while (changes > 0);
		return getCount(input, '#');
	}
	
	private static int countOccupied(char[][] input, int i, int j) {
		int occupied = 0;
		Direction[] dirs = Direction.values();
		for(Direction dir : dirs){
			Point p = new Point(i, j);
			p = dir.move(p, 1);
			while(p.getX()>=0 && p.getX()< input.length && p.getY()>=0 && p.getY()< input[0].length && input[p.getX()][p.getY()] != 'L'){
				if(input[p.getX()][p.getY()] == '#'){
					occupied++;
					break;
				}
				p = dir.move(p, 1);
			}
		}
		return occupied;
	}
}
