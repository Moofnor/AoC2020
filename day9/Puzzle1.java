package day9;

import utilities.InputLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Puzzle1 {
	public Puzzle1() {
		long[] input = Stream.of(InputLoader.asString(9).split("\r\n")).mapToLong(Long::parseLong).toArray();
		long[][] matrix = getSumMatrix(input, 25);
		int answer = getFaulty(matrix, 25);
		System.out.println("Answer: " + input[answer]);

	}

	long[][] getSumMatrix(long[] input, int preample) {
		long[][] matrix = new long[input.length + preample][preample];
		for (int i = 0; i<input.length; i++) {
			matrix[i+preample][0] = input[i];
			for (int j = 1; j < preample; j++) {
				matrix[i+preample][j] = input[i] + matrix[i+preample-j][0];
			}
		}
		return matrix;
	}

	int getFaulty(long[][] matrix, int preample) {
		int hits;
		for (int i = 2*preample; i<matrix.length; i++) {
			hits = 0;
			for (int j = 1; j < preample; j++) {
				for (int k = 1; k <= j; k++) {
					if (matrix[j + i - preample][k] == matrix[i][0])
						hits++;
				}
			}
			if (hits == 0) {
				return i - preample;
			}
		}
		return 0;
	}
}

