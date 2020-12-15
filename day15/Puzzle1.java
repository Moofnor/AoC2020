package day15;

import utilities.InputLoader;

import java.util.ArrayList;

public class Puzzle1 {
	public Puzzle1() {
		ArrayList<Long> input = new ArrayList<>();
		for (String s : InputLoader.asString(15).split(",")) {
			long l = Long.parseLong(s);
			input.add(l);
		}

		int j;
		long lastSpoken;
		boolean isSpoken;
		for (int i = input.size(); i < 2020; i++) {
			lastSpoken = input.get(i-1);
			isSpoken = false;
			for (j = i-2; j >= 0; j--) {
				if (input.get(j) == lastSpoken) {
					isSpoken = true;
					break;
				}
			}
			if (isSpoken) {
				input.add((long) (i - 1 - j));
			} else {
				input.add(0L);
			}
		}

		System.out.println("Answer: " + input.get(2020-1));
	}
}
