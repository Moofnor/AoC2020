package day14;

import utilities.InputLoader;

import java.util.HashMap;

public class Puzzle2 {
	HashMap<Long, Long> mem = new HashMap<Long, Long>();
	char[] mask;

	public Puzzle2() {
		String[] input = InputLoader.asString(14).split("\r\n");
		long value;
		String id;
		String[] parts;

		for (String in: input) {
			parts = in.split(" = ");
			if (parts[0].equals("mask")){
				this.mask = parts[1].toCharArray();
				continue;
			}
			id = String.format("%36s", Long.toBinaryString(Long.parseLong(parts[0].substring(4, parts[0].length()-1)))).replace(" ", "0");
			value = Long.parseLong(parts[1]);
			processInput(id, value);
		}
		long answer = 0;
		for (Long val: mem.values()) {
			answer += val;
		}
		System.out.println("answer: " + answer);

	}

	public void runMask(String fullId, String currentID, long value) {
		if (currentID.length() == fullId.length()) {
			mem.put(Long.parseLong(currentID, 2), value);
			return;
		}
		switch(mask[currentID.length()]) {
			case 'X':
				runMask(fullId, currentID + '0', value);
				runMask(fullId, currentID + '1', value);
				break;
			case '0':
				runMask(fullId, currentID + fullId.charAt(currentID.length()), value);
				break;
			case '1':
				runMask(fullId, currentID + '1', value);
				break;
		}
	}

	public void processInput(String id, long value) {
		assert id.length() == mask.length;
		runMask(id, "", value);
	}
}