package day22;

import utilities.InputLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle1 {
	public Puzzle1() {
		String[] input = InputLoader.asString(22).split("\r\n\r\n");
		List<Integer> deck1 = Arrays.stream(input[0].split(":\r\n")[1].split("\r\n")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		List<Integer> deck2 = Arrays.stream(input[1].split(":\r\n")[1].split("\r\n")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

		while (deck1.size() > 0 && deck2.size() > 0) {
			int d1 = deck1.remove(0);
			int d2 = deck2.remove(0);
			if (d1 > d2) {
				deck1.add(d1);
				deck1.add(d2);
			} else {
				deck2.add(d2);
				deck2.add(d1);
			}
		}


		long answer = 0;
		List<Integer> deck = deck1.size() > 0 ? deck1 : deck2;
		for (int i = 0; i<deck.size(); i++) {
			answer += deck.get(i) * (deck.size()-i);
		}
		System.out.println("Answer: " + answer);
	}
}
