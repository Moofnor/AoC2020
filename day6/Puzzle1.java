package day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.InputLoader;

import java.util.Arrays;

public class Puzzle1 {
	public Puzzle1() {
		String[] groups = InputLoader.asString(6).split("\r\n\r\n");
		int sum = 0;
		for (String group : groups) {
			sum += processGroup(group).length();
		}
		System.out.println("Answer: " + sum);
	}

	String processGroup(String group) {
		char[] groupArray = group.replace("\r\n","").toCharArray();
		Arrays.sort(groupArray);

		StringBuilder unique = new StringBuilder(""+groupArray[0]);
		for (int i=1; i< groupArray.length; i++) {
			if (groupArray[i-1] != groupArray[i]) {
				unique.append(groupArray[i]);
			}
		}
		return unique.toString();

	}
}

class Tester {
	static Puzzle1 pz;

	@BeforeAll
	static void setup() {
		pz = new Puzzle1();
	}

	@Test
	void groupTest() {
		Assertions.assertEquals("abcd", pz.processGroup("abc\r\ncbd"));
	}


}
