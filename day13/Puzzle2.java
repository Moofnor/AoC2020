package day13;

import utilities.InputLoader;
import java.util.List;

public class Puzzle2 {
	public Puzzle2() {
		String[] input = InputLoader.asString(13).split("\r\n");
		long timeStamp = Long.parseLong(input[0]);
		String[] IDs = input[1].split(",");
		Schedule s = new Schedule(timeStamp, IDs);
		long answer = s.remainderTheorem();
		System.out.println("Answer: " + answer);
	}
}
