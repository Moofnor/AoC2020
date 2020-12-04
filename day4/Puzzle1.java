package day4;

import utilities.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle1 {
	public Puzzle1() {
		List<String> input = InputLoader.asList(4);
		int valid = 0;
		assert input != null;
		String passport = "";
		for (String line: input) {
			if (line.isEmpty()) {
				if (isValid(passport))
					valid++;
				passport = "";
			}
			else {
				passport = passport.concat(" " + line);
			}
		}
		// Check last passport
		if (isValid(passport))
			valid++;
		System.out.println("Answer: "+ valid);
	}

	private boolean isValid(String passport) {
//		System.out.println(passport);
		String[] parts = passport.split(" ");
		List<String> required = new ArrayList<>();
		required.add("byr");
		required.add("iyr");
		required.add("eyr");
		required.add("hgt");
		required.add("hcl");
		required.add("ecl");
		required.add("pid");

		for (String part : parts) {
			String match = "";
			for (String req : required) {
				if (part.strip().startsWith(req)) {
					match = req;
				}
			}
			required.remove(match);
		}
		return required.size() == 0;
	}

}
