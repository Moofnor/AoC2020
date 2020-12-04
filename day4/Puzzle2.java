package day4;

import utilities.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle2 {
	public Puzzle2() {
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

		String[] parts = passport.split(" ");
		List<String> required = getRequirements();

		for (String part : parts) {
			String match = checkRequirements(required, part);
			required.remove(match);
		}

		return required.size() == 0;
	}

	private String checkRequirements(List<String> requirements, String part) {
		String[] req = part.split(":");
		switch (req[0]) {
			case "byr":
				if (isValidInt(req[1], 1920, 2002)) return "byr";
				break;
			case "iyr":
				if (isValidInt(req[1], 2010, 2020)) return "iyr";
				break;
			case "eyr":
				if (isValidInt(req[1], 2020, 2030)) return "eyr";
				break;
			case "hgt":
				if (isValidHeight(req)) return "hgt";
				break;
			case "hcl":
				if (isValidHex(req[1])) return "hcl";
				break;
			case "ecl":
				if (isValidColor(req[1])) return "ecl";
				break;
			case "pid":
				if (isValidNumber(req[1])) return "pid";
				break;
		}
		return "";
	}

	private boolean isValidHeight(String[] req) {
		if (req[1].endsWith("cm")) {
			return (isValidInt(req[1].split("cm")[0], 150, 193));
		}
		else if (req[1].endsWith("in")) {
			return (isValidInt(req[1].split("in")[0], 59, 76));
		}
		return false;
	}

	private boolean isValidNumber(String s) {
		if (s.length() == 9) {
			for (int i = 0; i < s.length(); i++) {
				if (Character.digit(s.charAt(i), 10) == -1)
					return false;
			}
			return true;
		}
		return false;
	}

	private boolean isValidColor(String s) {
		List<String> colors = Arrays.asList("amb","blu","brn","gry","grn","hzl","oth");
		for (String col: colors) {
			if (s.equals(col)) return true;
		}
		return false;
	}

	private boolean isValidHex(String s) {
		int len = s.length();
		if (len==7 && s.startsWith("#")){
			for (int i = 1; i < len; i++){
				if (Character.digit(s.charAt(i), 16) == -1)
					return false;
			}
			return true;
		}
		return false;
	}

	private boolean isValidInt(String s, int i, int i2) {
		try {
			int year = Integer.parseInt(s);
			if (year >= i && year <= i2)
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	private List<String> getRequirements() {
		List<String> required = new ArrayList<>();
		required.add("byr");
		required.add("iyr");
		required.add("eyr");
		required.add("hgt");
		required.add("hcl");
		required.add("ecl");
		required.add("pid");
		return required;
	}

}
