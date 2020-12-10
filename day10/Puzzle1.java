package day10;

import utilities.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Puzzle1 {
	public Puzzle1() {
		int[] input = Stream.of(InputLoader.asString(10).split("\r\n")).mapToInt(Integer::parseInt).sorted().toArray();
		int[] differences = IntStream.range(0, input.length - 1).map(i -> input[i + 1] - input[i]).toArray();
		long sumOf3 = Arrays.stream(differences).filter(diff -> diff == 3).count();
		long sumOf1 = Arrays.stream(differences).filter(diff -> diff == 1).count();

		System.out.println("Answer: " + (sumOf1 * (sumOf3 + 1)));
	}
}
