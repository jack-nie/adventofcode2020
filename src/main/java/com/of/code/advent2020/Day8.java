package com.of.code.advent2020;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 {
	public static void main(String[] args) {
		try {
			URI uri = Day8.class.getResource("/day8.txt").toURI();
			Stream<String> lines = Files.lines(Path.of(uri));
			List<Instruction> instructions = convertToInstructions(lines);
			int i = part1(instructions);
			System.out.println(i);
			int j = part2(instructions);
			System.out.println(j);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}

	static int part2(List<Instruction> instructions) {
		return correctInstruction(instructions);
	}

	static int part1(List<Instruction> instructions) {
		return calculateAcc(instructions).getResult();
	}

	private static int correctInstruction(List<Instruction> instructions) {
		Result result = new Result(-1, false);
		for (int i = 0; i < instructions.size(); i++) {
			Instruction item = instructions.get(i);

			switch (item.getOperation()) {
			case nop:
				item.setOperation(Operation.jmp);
				result = calculateAcc(instructions);
				if (!result.getFlag()) {
					return result.getResult();
				}
				item.setOperation(Operation.nop);
				break;
			case jmp:
				item.setOperation(Operation.nop);
				result = calculateAcc(instructions);
				if (!result.getFlag()) {
					return result.getResult();
				}
				item.setOperation(Operation.jmp);
				break;
			case acc:
				break;
			}

		}
		return result.getResult();

	}

	private static Result calculateAcc(List<Instruction> instructions) {
		int result = 0;
		int index = 0;
		Set<Instruction> visitedSet = new HashSet<>();

		while (index < instructions.size()) {
			Instruction instruction = instructions.get(index);
			if (visitedSet.contains(instruction)) {
				return new Result(result, true);
			}

			visitedSet.add(instruction);
			switch (instruction.getOperation()) {
			case acc:
				result += instruction.getArgument();
				index++;
				break;
			case jmp:
				index += instruction.getArgument();
				break;
			case nop:
				index++;
				break;
			default:
				break;
			}
		}
		return new Result(result, false);
	}

	private static List<Instruction> convertToInstructions(Stream<String> lines) {
		return lines.map(Day8::convertToInstruction).collect(Collectors.toList());
	}

	private static Instruction convertToInstruction(String line) {
		String[] stringArray = line.split(" ");
		return new Instruction(Operation.valueOf(stringArray[0]), Integer.parseInt(stringArray[1]));
	}

	enum Operation {
		nop, acc, jmp
	}

	static class Instruction {
		private Operation operation;
		private int argument;

		public Instruction(Operation operation, int argument) {
			this.operation = operation;
			this.argument = argument;
		}

		public Operation getOperation() {
			return operation;
		}

		public void setOperation(Operation operation) {
			this.operation = operation;
		}

		public int getArgument() {
			return argument;
		}

		public void setArgument(int argument) {
			this.argument = argument;
		}
	}

	static class Result {
		private final int result;
		private final boolean flag;

		public Result(int result, boolean flag) {
			this.result = result;
			this.flag = flag;
		}

		public int getResult() {
			return result;
		}

		public boolean getFlag() {
			return flag;
		}
	}
}
