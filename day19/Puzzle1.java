package day19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.InputLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Puzzle1 {
	public Puzzle1() {
		String[] input = InputLoader.asString(19).split("\r\n\r\n");
		Validator v = new Validator();
		v.processRules(input[0].split("\r\n"));
		int answer = 0;
		for (String message: input[1].split("\r\n")) {
			if (v.isValidMessage(message)) {
				answer++;
			}

		}
		System.out.println("Answer: " + answer);

	}
}

class Validator {
	String[] validMessages;
	HashMap<Integer, String> rulesList;
	HashMap<Integer, String[]> validMap = new HashMap<>();

	public boolean isValidMessage(String message) {
		if (validMessages == null) {
			getValidMessage(0);
		}
		for (String validMessage: validMessages) {
			if(validMessage.equals(message)) {
				return true;
			}
		}
		return false;
	}

	public void processRules(String[] rules) {
		rulesList = new HashMap<>();
		for (String rule: rules) {
			String parts[] = rule.split(": ");
			Integer key = Integer.parseInt(parts[0]);
			rulesList.put(key, parts[1]);
		}
	}

	public String[] getValidMessage(Integer ruleKey) {
		if (validMap.containsKey(ruleKey))
			return validMap.get(ruleKey);
		List<String> validMessages = new ArrayList<>();
		String[] options = rulesList.get(ruleKey).split(" \\| ");
		if (options[0].equals("\"a\"") || options[0].equals("\"b\""))
			validMessages.add(options[0].replace("\"", ""));
		else {
			for (String option : options) {
				String[] indexes = option.split(" ");
				String[] validMessages1 = getValidMessage(Integer.parseInt(indexes[0]));
				String[] validMessages2 = indexes.length <= 1 ? new String[]{""} : getValidMessage(Integer.parseInt(indexes[1]));
				String[] validMessages3 = indexes.length <= 2 ? new String[]{""} : getValidMessage(Integer.parseInt(indexes[2]));
				for (String part1 : validMessages1) {
					for (String part2 :  validMessages2) {
						for (String part3 : validMessages3) {
							validMessages.add(part1 + part2 + part3);
						}
					}
				}
			}
		}
		String[] arrayValid = validMessages.toArray(String[]::new);
		if (ruleKey == 0)
			this.validMessages = arrayValid;
		else
			validMap.put(ruleKey, arrayValid);
		return arrayValid;
	}
}

class TestValidator {
	static Validator v;
	@BeforeAll
	static void setup() {
		v = new Validator();

		String[] testRules = {"0: 4 1 5",
				"1: 2 3 | 3 2",
				"2: 4 4 | 5 5",
				"3: 4 5 | 5 4",
				"4: \"a\"",
				"5: \"b\"",
		};
		v.processRules(testRules);
	}

	@Test
	void rule4ShouldReturna() {
		Assertions.assertEquals("a", v.getValidMessage(4)[0]);
	}

	@Test
	void rule2ShouldReturnaaAndbb() {
		Assertions.assertEquals("aa", v.getValidMessage(2)[0]);
		Assertions.assertEquals("bb", v.getValidMessage(2)[1]);
	}

	@Test
	void ababbbShouldBeValid() {
		v.getValidMessage(0);
		Assertions.assertTrue(v.isValidMessage("ababbb"));
	}

	@Test
	void bababaShouldBeInvalid() {
		v.getValidMessage(0);
		Assertions.assertFalse(v.isValidMessage("bababa"));
	}
}

