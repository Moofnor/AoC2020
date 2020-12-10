package day10;

import utilities.InputLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Puzzle2 {
	public Puzzle2() {
		int[] input = Stream.of(InputLoader.asString(10).split("\r\n")).mapToInt(Integer::parseInt).sorted().toArray();
		int[] differences = IntStream.range(0, input.length - 1).map(i -> input[i + 1] - input[i]).toArray();

		long combs = 1;
		int i = 0;
		int seq;
		while (true) {
			seq = 0;
			while (i < differences.length && differences[i] == 1) {
				i++;
				seq++;
			}

			if (seq > 1 && seq <= 3) {
				combs *= Math.pow(2, seq-1);
			} else if (seq == 4) {
				combs *= 7;
			} else if (seq==5) {
				combs *= 13;
			}

			if (i >= differences.length) {
				break;
			}
			i++;
		}

		System.out.println("Answer: " + combs);
	}
}
