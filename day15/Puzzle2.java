package day15;

import utilities.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle2 {
	public Puzzle2() {
		HashMap<Integer, Integer> input = new HashMap<>();
		String[] inputString = InputLoader.asString(15).split(",");
		for (int i = 0; i < inputString.length-1; i++) {
			int l = Integer.parseInt(inputString[i]);
			input.put(l, i);
		}

		int nTh = 30000000;

		int newSpoken = 0;
		int lastSpoken = Integer.parseInt(inputString[inputString.length-1]);
		for (int i = input.size()+1; i < nTh; i++) {
			if (input.containsKey(lastSpoken)) {
				newSpoken = lastSpoken;
				lastSpoken = i - 1 - input.get(lastSpoken);
				input.put(newSpoken, i-1);
			} else {
				newSpoken = lastSpoken;
				lastSpoken = 0;
				input.put(newSpoken, i-1);
			}

		}

		System.out.println("Answer: " + lastSpoken);
	}
}