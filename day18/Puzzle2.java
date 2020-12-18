package day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.InputLoader;

import java.util.ArrayList;
import java.util.List;

public class Puzzle2 {
	public Puzzle2() {
		String[] input = InputLoader.asString(18).split("\r\n");
		Calculator2 c = new Calculator2();
		long answer = 0;
		for (String sumString: input)
			answer += c.processSum(sumString);
		System.out.println("Answer: " + answer);
	}
}

class Calculator2 {
	public long processSum(String sumString) {
		long answer = 0;
		int index = 0, end = 0;
		char sumChar;
		List<String> sumList= new ArrayList<>();
		while (index < sumString.length()) {
			sumChar = sumString.charAt(index);
			if (sumChar == '('){
				end = getNextStart(sumString, index);
				sumList.add(""+processSum(sumString.substring(index+1, end)));
				index = end;
			} else if (sumChar == ' ') {

			} else {
				sumList.add(""+sumString.charAt(index));
			}
			index++;
		}

		int i = 1;
		boolean finalize = false;
		while (sumList.size() > 1) {
			if (sumList.get(i).equals("+")) {
				sumList.set(i-1, ""+evaluateOperator(sumList.get(i-1), sumList.get(i+1), 'a'));
				sumList.remove(i);
				sumList.remove(i);
				i = 1;
			} else if (sumList.get(i).equals("*")) {
				if (finalize) {
					sumList.set(i-1, ""+evaluateOperator(sumList.get(i-1), sumList.get(i+1), 'm'));
					sumList.remove(i);
					sumList.remove(i);
					i = 1;
				} else {
					i += 2;
					if (i >= sumList.size()) {
						finalize = true;
						i = 1;
					}
				}
			}
		}
		return Long.parseLong(sumList.get(0));
	}

	private int getNextStart(String sumString, int start) {
		int sumOfParenthesis = -1;
		while (sumOfParenthesis != 0 && start < sumString.length()) {
			start++;
			if (sumString.charAt(start) == ')')
				sumOfParenthesis++;
			else if (sumString.charAt(start) == '(')
				sumOfParenthesis--;
		}
		return start;
	}

	private long evaluateOperator(String si, String sj, char op) {
		long i = Long.parseLong(si);
		long j = Long.parseLong(sj);
		if (op == 'a') {
			return i + j;
		} else {
			return i * j;
		}
	}
}

class Tester2 {
	static Calculator2 c;
	@BeforeAll
	static void setup() {
		c = new Calculator2();
	}

	@Test
	void simpleSum() {
		String input = "1 + 2 * 3 + 4 * 5 + 6";
		Assertions.assertEquals(231, c.processSum(input));
	}

	@Test
	void parenthesisTest() {
		String input = "1 + (2 * 3) + (4 * (5 + 6))";
		Assertions.assertEquals(51, c.processSum(input));
	}

	@Test
	void parenthesisTest2() {
		String input = "2 * 3 + (4 * 5)";
		Assertions.assertEquals(46, c.processSum(input));
	}

	@Test
	void parenthesisTest3() {
		String input = "5 + (8 * 3 + 9 + 3 * 4 * 3)";
		Assertions.assertEquals(1445, c.processSum(input));
	}

	@Test
	void parenthesisTest4() {
		String input = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2";
		Assertions.assertEquals(23340, c.processSum(input));
	}
}
