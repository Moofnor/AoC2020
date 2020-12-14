package day14;

import utilities.InputLoader;

import java.util.HashMap;

public class Puzzle1 {
	long mask1 = 0, mask0 = 0;
	HashMap<Long, Long> mem = new HashMap<Long, Long>();

	public Puzzle1() {
		String[] input = InputLoader.asString(14).split("\r\n");
		long id, value;
		String[] parts;

		for (String in: input) {
			parts = in.split(" = ");
			if (parts[0].equals("mask")){
				setMask(parts[1]);
				continue;
			}
			id = Long.parseLong(parts[0].substring(4, parts[0].length()-1));
			value = Long.parseLong(parts[1]);
			mem.put(id, processValue(value));
		}
		long answer = 0;
		for (Long val: mem.values()) {
			answer += val;
		}
		System.out.println("answer: " + answer);

	}

	public void setMask(String mask) {
		mask1 = Long.parseLong(mask.replace("X", "0"), 2);
		mask0 = Long.parseLong(mask.replace("X", "1"), 2);
	}

	public long processValue(long value) {
		return (value & mask0) | mask1;
	}
}