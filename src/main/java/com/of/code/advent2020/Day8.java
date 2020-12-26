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
            int i = part1(lines);
            System.out.println(i);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    static int part1(Stream<String> lines) {
        List<Instruction> instructions = convertToInstructions(lines);
        return calculateAcc(instructions);
    }

    private static int calculateAcc(List<Instruction> instructions) {
        int result = 0;
        int index = 0;
        Set<Instruction> visitedSet = new HashSet<>();

        while (index < instructions.size()) {
            Instruction instruction = instructions.get(index);
            if (visitedSet.contains(instruction)) {
                return result;
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
        return result;
    }

    private static List<Instruction> convertToInstructions(Stream<String> lines) {
        return lines.map(Day8::convertToInstruction).collect(Collectors.toList());
    }

    private static Instruction convertToInstruction(String line) {
        String[] stringArray = line.split(" ");
        return new Instruction(Operation.valueOf(stringArray[0]), Integer.parseInt(stringArray[1]));
    }

    enum Operation {
        nop,acc,jmp
    }

    static class Instruction {
        private final Operation operation;
        private final int argument;

        public Instruction(Operation operation, int argument) {
            this.operation = operation;
            this.argument = argument;
        }

        public Operation getOperation() {
            return operation;
        }

        public int getArgument() {
            return argument;
        }
    }
}
