package com.of.code.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Helper {
	public static List<Integer> generateIntArrayFromFile(String path) {
		URL resource = Day1_1.class.getResource(path);

		File file = new File(resource.getPath());
		Scanner scanner;
		List<Integer> list = new ArrayList<Integer>();

		try {
			scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				list.add(Integer.parseInt(scanner.nextLine()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> generateStringArrayFromFile(String path) {
		URL resource = Day1_1.class.getResource(path);

		File file = new File(resource.getPath());
		Scanner scanner;
		List<String> list = new ArrayList<String>();

		try {
			scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				list.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
