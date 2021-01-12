package com.of.code.advent2020;

import static java.lang.Math.abs;

import java.util.Arrays;

public enum Direction {
	NORTH(1, 'U'), EAST(4, 'R'), SOUTH(2, 'D'), WEST(3, 'L'), NORTHEAST(4, 'E'), EASTSOUTH(5, 'E'), SOUTHWEST(6, 'E'),
	WESTNORTH(7, 'E');

	public final int num;
	public final int code;

	Direction(int num, char code) {
		this.num = num;
		this.code = code;
	}


	public Point move(Point currentLocation, int amount) {
		switch (this) {
		case SOUTH:
			return new Point(currentLocation.getX(), currentLocation.getY() + amount);
		case NORTH:
			return new Point(currentLocation.getX(), currentLocation.getY() - amount);
		case EAST:
			return new Point(currentLocation.getX() + amount, currentLocation.getY());
		case WEST:
			return new Point(currentLocation.getX() - amount, currentLocation.getY());
		case SOUTHWEST:
			return new Point(currentLocation.getX() - amount, currentLocation.getY() + amount);
		case NORTHEAST:
			return new Point(currentLocation.getX() + amount, currentLocation.getY() - amount);
		case EASTSOUTH:
			return new Point(currentLocation.getX() + amount, currentLocation.getY() + amount);
		case WESTNORTH:
			return new Point(currentLocation.getX() - amount, currentLocation.getY() - amount);
		}
		throw new IllegalStateException("Non-existent Direction: " + this);
	}

	public char getInGrid(char[][] grid, Point p, char none) {
		if (p.getX() >= 0 && p.getX() < grid.length && p.getY() >= 0 && p.getY() < grid[0].length) {
			return grid[p.getX()][p.getY()];
		}
		return none;
	}

	public char getInGrid(char[][] grid, Point p) {
		return getInGrid(grid, p, '.');
	}

	public Point move(Point p) {
		return move(p, 1);
	}

}