package day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.InputLoader;

import java.util.Arrays;

public class Puzzle2 {
	public Puzzle2() {
		String[] groups = InputLoader.asString(6).split("\r\n\r\n");
		int sum = 0;
		for (String group : groups) {
			sum += processGroup(group).length();
		}
		System.out.println("Answer: " + sum);
	}

	String processGroup(String group) {
		String[] answers = group.split("\r\n");
		String matching = answers[0];

		for (int i = 1; i < answers.length; i++) {

			char[] match = matching.toCharArray();
			matching = "";
			for (char matchChar: match) {
				if (answers[i].contains("" + matchChar)) {
					matching += matchChar;
				}
			}
		}
		return matching;
	}
}

class Tester2 {
	static Puzzle2 pz;

	@BeforeAll
	static void setup() {
		pz = new Puzzle2();
	}

	@Test
	void groupTest() {
		Assertions.assertEquals("bc", pz.processGroup("abc\r\ncbd"));
	}


}
