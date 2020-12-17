package day16;

import utilities.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Puzzle2 {
	public Puzzle2() {
		String[] inputParts = InputLoader.asString(16).split("\r\n\r\n");

		HashMap<String, int[]> validRanges = getValidRanges(inputParts[0].split("\r\n"));
		String[] cardOrder = getCardOrder(inputParts[2].split("\r\n"), validRanges);




		int answer = 0;
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

	private List<int[]> getValidCards(String[] cards, HashMap<String, int[]> validRanges) {
		List<int[]> validCards = new ArrayList<>();
		for (int i = 1; i < cards.length; i++) {
			int[] values = Arrays.stream(cards[i].split(",")).mapToInt(Integer::parseInt).toArray();
			boolean valid = true;
			for (int value : values) {
				if (!isValidValue(value, validRanges)) {
					valid = false;
					break;
				}
			}
			if (valid)
				validCards.add(values);
		}
		return validCards;
	}

	private String[] getCardOrder(String[] cards, HashMap<String, int[]> validRanges) {
//		int[][] valueMatrix = new int[cards.length-1][validRanges.size()];
		String[] keyList = validRanges.keySet().toArray(new String[0]);
		boolean[][] validKeys = new boolean[keyList.length][keyList.length];


		for (int i = 1; i < cards.length; i++) {
			int[] values = Arrays.stream(cards[i].split(",")).mapToInt(Integer::parseInt).toArray();
			boolean allValid = true;
			for (int k = 0; k < values.length; k++) {
				int value = values[k];
				if (isValidValue(value, validRanges)) {
					for (int j = 0; j < validRanges.size(); j++) {
						int[] range = validRanges.get(keyList[j]);
						if ((value < range[0] || value > range[3]) || (value > range[1] && value < range[2])) {
							validKeys[j][k] = true; ;
						}
					}
				}
			}
		}
		return keyList;
	}

	private boolean isValidValue(int value, HashMap<String, int[]> validRanges) {
		for (int[] range: validRanges.values()) {
			if ((value >= range[0] && value <= range[3]) && (value <= range[1] || value >= range[2]))
				return true;
		}
		return false;
	}



}