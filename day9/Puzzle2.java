package day9;

import utilities.InputLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Puzzle2 {
	public Puzzle2() {
		long[] input = Stream.of(InputLoader.asString(9).split("\r\n")).mapToLong(Long::parseLong).toArray();
		long sum, answer = 0;
		int j;
		List<Long> numbers;

		for (int i=0; i < input.length; i++){
			sum = input[i];
			numbers =  new ArrayList<Long>();
			numbers.add(input[i]);
			j = 1;
			while (sum < 1309761972) {
				sum += input[i + j];
				numbers.add(input[i+j]);
				j++;
			}
			if (sum == 1309761972) {
				Collections.sort(numbers);
				answer = numbers.get(0) + numbers.get(numbers.size()-1);
				break;
			}

		}

		System.out.println("Answer: " + answer);
	}
}
