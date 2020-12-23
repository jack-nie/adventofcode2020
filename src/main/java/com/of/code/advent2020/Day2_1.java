package com.of.code.advent2020;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day2_1 {
	public static void main(String[] args) {
	
		long l = 0;
		try {
			l = Files.lines(Paths.get(Day2_1.class.getResource("/day2.txt").toURI())).filter(line -> {
			    String policy = line.split(":")[0];
			    int minVal = Integer.valueOf(policy.split("-")[0]);
			    int maxVal = Integer.valueOf(policy.split("-")[1].split(" ")[0]);
			    String letterToContain = policy.substring(policy.length() - 1);

			    String password = line.split(":")[1].substring(1);
			 
			    
			    int count = 0;
			    for(int i=0; i < password.length(); i++) {
			        if (password.charAt(i) == letterToContain.charAt(0)) {
			            count++;
			        }
			    }

			    if(minVal <= count && count <= maxVal) {
			        return true;
			    }
			    return false;
			 }).count();
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		String str = "5-11 x: lgqsgxxpzprx";
		System.out.println(str.split(":")[1].substring(1));
		System.out.println(l);
	}
}
