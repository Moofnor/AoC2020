package day11;

import utilities.InputLoader;

import java.util.Arrays;
import java.util.List;

public class Puzzle2 {
	public Puzzle2() {
		String[] layoutString = InputLoader.asString(11).split("\r\n");
		char[][] layout = new char[layoutString.length][layoutString[0].length()];
		for (int i=0; i < layoutString.length; i++){
			layout[i] = layoutString[i].toCharArray();
		}
		Ferry fe = new Ferry(layout);
		char[][] newState = fe.currentState;
		do {
			fe.currentState = newState;
			newState = fe.getNewState2();
		} while (!Arrays.deepEquals(fe.currentState, newState));
		int answer = fe.getOccupancy();
		System.out.println("Answer: " + answer);
	}
}

