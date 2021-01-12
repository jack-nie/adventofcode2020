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

	public static Direction getByDirCode(char code) {
		return Arrays.stream(values()).filter(e -> e.code == code).findAny().get();
	}

	public static Direction getByDir(char code) {
		return Arrays.stream(values()).filter(e -> e.name().charAt(0) == code).findAny().get();
	}

	public Direction turn(boolean right) {
		int cur = ordinal() + (right ? 1 : -1);
		if (cur == fourDirections().length)
			cur = 0;
		else if (cur == -1)
			cur = 3;
		return fourDirections()[cur];
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

	public Direction opposite() {
		switch (this) {
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;
		case EAST:
			return WEST;
		case WEST:
			return EAST;
		case NORTHEAST:
			return SOUTHWEST;
		case SOUTHWEST:
			return NORTHEAST;
		case EASTSOUTH:
			return WESTNORTH;
		case WESTNORTH:
			return EASTSOUTH;
		}
		throw new IllegalStateException("Non-existent Direction: " + this);
	}

	public static Direction getByMove(Point from, Point to) {
		if (to.getX() > from.getX())
			return EAST;
		else if (to.getX() < from.getX())
			return WEST;
		else if (to.getY() > from.getY())
			return SOUTH;
		else if (to.getY() < from.getY())
			return NORTH;
		throw new IllegalStateException("From and to location are the same: " + from + ", " + to);
	}

	public boolean leftOf(Direction robotDir) {
		int n = this.ordinal() - 1;
		if (n == -1)
			n = values().length - 1;
		return robotDir.ordinal() == n;
	}

	public Direction turnDegrees(int degrees, boolean right) {
		int num = degrees % 360;
		Direction dir = this;
		while (num > 0) {
			dir = turn(right);
			num -= 90;
		}
		return dir;
	}

	public Direction turnDegrees(int degrees) {
		return turnDegrees(abs(degrees), degrees > 0);
	}

	public static Point turn(Point w, boolean b) {
		return b ? new Point(-w.getY(), w.getX()) : new Point(w.getY(), -w.getX());
	}

	public static Point turnDegrees(Point w, int distance, boolean b) {
		int num = distance % 360;
		while (num > 0) {
			w = turn(w, b);
			num -= 90;
		}
		return w;
	}

	public static Point turnDegrees(Point w, int distance) {
		return turnDegrees(w, abs(distance), distance > 0);
	}

	public static Direction[] fourDirections() {
		return new Direction[] { NORTH, EAST, SOUTH, WEST };
	}
}