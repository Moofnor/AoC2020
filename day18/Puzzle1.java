package day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.InputLoader;
import java.util.List;

public class Puzzle1 {
	public Puzzle1() {
		String[] input = InputLoader.asString(18).split("\r\n");
		Calculator c = new Calculator();
		long answer = 0;
		for (String sumString: input)
			answer += c.processSum(sumString, 0);
		System.out.println("Answer: " + answer);
	}
}

class Calculator {
	public long processSum(String sumString, int start) {
		long answer = 0;
		char op = 'a';
		for ( ; start < sumString.length(); start++) {
			switch (sumString.charAt(start)){
				case '+':
					op = 'a';
					break;
				case '*':
					op = 'm';
					break;
				case '(':
					answer = evaluateOperator(answer, processSum(sumString, start+1), op);
					start = getNextStart(sumString, start);
					break;
				case ')':
					return answer;
				case ' ':
					break;
				default:
					// is a number
					long num = Character.getNumericValue(sumString.charAt(start));
					answer = evaluateOperator(answer, num, op);
			}
		}
		return answer;
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

	private long evaluateOperator(long i, long j, char op) {
		if (op == 'a') {
			return i + j;
		} else {
			return i * j;
		}
	}
}

class Tester {
	static Calculator c;
	@BeforeAll
	static void setup() {
		c = new Calculator();
	}

	@Test
	void simpleSum() {
		String input = "1 + 2 * 3 + 4 * 5 + 6";
		Assertions.assertEquals(71, c.processSum(input, 0));
	}

	@Test
	void parenthesisTest() {
		String input = "1 + (2 * 3) + (4 * (5 + 6))";
		Assertions.assertEquals(51, c.processSum(input, 0));
	}
}




