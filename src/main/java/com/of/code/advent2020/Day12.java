package com.of.code.advent2020;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 {
	public static void main(String[] args) {
		List<String> list = Helper.generateStringArrayFromFile("/day12.txt");
		int part1 = part1(list);
		System.out.println(part1);

		int part2 = part2(list);
		System.out.println(part2);
	}

	public static int part1(List<String> list) {
		Point point = parsePosition(list);
		return Math.abs(point.getX()) + Math.abs(point.getY());
	}

	public static Point parsePosition(List<String> list) {
		List<Move> moves = list.stream().map(item -> new Move(Direction.valueOf(String.valueOf(item.charAt(0))),
				Integer.parseInt(item.substring(1)))).collect(Collectors.toList());
		Point point = new Point(0, 0, Direction.E);
		for (int i = 0; i < moves.size(); i++) {
			Move move = moves.get(i);
			switch (move.direction) {
			case N:
				point.setY(point.getY() + move.getDistance());
				break;
			case S:
				point.setY(point.getY() - move.getDistance());
				break;
			case W:
				point.setX(point.getX() - move.getDistance());
				break;
			case E:
				point.setX(point.getX() + move.getDistance());
				break;
			case F:
				switch (point.getDirection()) {
				case N:
					point.setY(point.getY() + move.getDistance());
					break;
				case S:
					point.setY(point.getY() - move.getDistance());
					break;
				case W:
					point.setX(point.getX() - move.getDistance());
					break;
				case E:
					point.setX(point.getX() + move.getDistance());
					break;
				default:
					break;
				}
				break;
			case L:
				point.setDirection(calcDirection(point.getDirection(), Direction.L, move.getDistance()));
				break;
			case R:
				point.setDirection(calcDirection(point.getDirection(), Direction.R, move.getDistance()));
				break;

			}
		}

		return point;
	}

	public static int part2(List<String> list) {
		List<Move> moves = list.stream().map(item -> new Move(Direction.valueOf(String.valueOf(item.charAt(0))),
				Integer.parseInt(item.substring(1)))).collect(Collectors.toList());
		Point point = new Point(0, 0, Direction.E);
		Point point2 = new Point(10, 1, Direction.E);
		for (int i = 0; i < moves.size(); i++) {
			Move move = moves.get(i);
			switch (move.direction) {
			case N:
				point2.setY(point2.getY() + move.getDistance());
				break;
			case S:
				point2.setY(point2.getY() - move.getDistance());
				break;
			case W:
				point2.setX(point2.getX() - move.getDistance());
				break;
			case E:
				point2.setX(point2.getX() + move.getDistance());
				break;
			case F:
				point.setY(point.getY() + point2.getY() * move.getDistance());
				point.setX(point.getX() + point2.getX() * move.getDistance());
				break;
			case L:
				point2 = calcDirectionPart2(point2, Direction.L, move.getDistance());
				break;
			case R:
				point2 = calcDirectionPart2(point2, Direction.R, move.getDistance());
				break;

			}
		}

		return Math.abs(point.getX()) + Math.abs(point.getY());
	}

	private static Direction calcDirection(Direction oldDirection, Direction newDirection, int degree) {
		int step = 0;
		switch (newDirection) {
		case L:
			step = (360 - degree) / 90;
			break;
		case R:
			step = degree / 90;
			break;
		default:
			break;
		}

		List<Direction> directions = Arrays.asList(Direction.E, Direction.S, Direction.W, Direction.N);
		int index = directions.indexOf(oldDirection);
		return directions.get((index + step) % 4);
	}

	private static Point calcDirectionPart2(Point point, Direction newDirection, int degree) {
		int step = 0;
		switch (newDirection) {
		case L:
			step = (360 - degree) / 90 & 4;
			break;
		case R:
			step = degree / 90 % 4;
			break;
		default:
			break;
		}

		int x = 0;
		int y = 0;
		switch (step) {
		case 0:
			break;
		case 1:
			x = point.getX() * -1;
			y = point.getY();
			point.setX(y);
			point.setY(x);
			break;
		case 2:
			x = point.getX() * -1;
			y = point.getY() * -1;
			point.setY(y);
			point.setX(x);
			break;
		case 3:
			y = point.getY() * -1;
			x = point.getX();
			point.setY(x);
			point.setX(y);
			break;
		}
		return point;
	}

	enum Direction {
		N, S, W, E, L, R, F
	}

	static class Move {
		private Direction direction;
		private int distance;

		public Move(Direction direction, int distance) {
			this.direction = direction;
			this.distance = distance;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}

		public int getDistance() {
			return distance;
		}

		public void setDistance(int distance) {
			this.distance = distance;
		}

	}

	static class Point {
		private int x;
		private int y;
		private Direction direction;

		public Point(int x, int y, Direction direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}
	}
}
