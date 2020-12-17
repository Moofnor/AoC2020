package day16;

import org.junit.jupiter.api.BeforeEach;
import utilities.InputLoader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle1 {
	public Puzzle1() {
		String[] inputParts = InputLoader.asString(16).split("\r\n\r\n");

		HashMap<String, int[]> validRanges = getValidRanges(inputParts[0].split("\r\n"));
		int answer = getErrorRate(inputParts[2].split("\r\n"), validRanges);
		System.out.println("Answer: " + answer);
	}

	private HashMap<String, int[]> getValidRanges(String[] ranges) {
		HashMap<String, int[]> validRanges = new HashMap();

		for (String range: ranges) {
			String[] matches = range.split(" ");
			if (matches.length != 4)
				continue;
			String[] range1 = matches[1].split("-");
			String[] range2 = matches[3].split("-");
			int[] intRange = {Integer.parseInt(range1[0]), Integer.parseInt(range1[1]), Integer.parseInt(range2[0]), Integer.parseInt(range2[1])};
			validRanges.put(matches[0].substring(0, matches[0].length()-1), intRange);
		}

		return validRanges;
	}

	private int getErrorRate(String[] cards, HashMap<String, int[]> validRanges) {
		int errorRate = 0;
		for (int i = 1; i < cards.length; i++) {
			int[] values = Arrays.stream(cards[i].split(",")).mapToInt(Integer::parseInt).toArray();
			for (int value : values) {
				if (!isValidValue(value, validRanges)) {
					errorRate += value;
				}
			}
		}
		return errorRate;
	}

	private boolean isValidValue(int value, HashMap<String, int[]> validRanges) {
		for (int[] range: validRanges.values()) {
			if ((value >= range[0] && value <= range[3]) && (value <= range[1] || value >= range[2]))
				return true;
		}
		return false;
	}

}
