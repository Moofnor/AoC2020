package day7;

import utilities.InputLoader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle1 {
	Set<String> answer = new HashSet();

	public Puzzle1() {
		String[] input = InputLoader.asString(7).split("\r\n");
		HashMap<String, Bag> bags = new HashMap<>();
		Bag bag;
		for (String bagString: input) {
			bag = new Bag(bagString.split(" contain "));
			bags.put(bag.getColor(), bag);
		}
		getContainingBags("shiny gold", bags);
		System.out.println("Answer: " + answer.size());
	}

	public void getContainingBags(String color, HashMap<String, Bag> bags) {
		for (Bag bag: bags.values()) {
			if (bag.doesContain(color)) {
				answer.add(bag.getColor());
				getContainingBags(bag.getColor(), bags);
			}
		}
	}
}

class Bag {

	private final String color;
	public HashMap<String, Integer> contains = new HashMap<>();

	public Bag(String[] input) {
		this(input[0], input[1]);
	}

	public Bag(String color, String containString) {
		this.color = color.substring(0, color.length()-5);
		processContainString(containString);
	}

	private void processContainString(String containString) {
		Matcher m = Pattern.compile("[1-9] [a-z]+ [a-z]+ bag").matcher(containString);
		while (m.find()) {
			String group = m.group();
			int amount = Integer.parseInt(group.substring(0,1));
			String color = group.substring(2, group.length()-4);
			contains.put(color, amount);
		}
	}

	public String getColor() {
		return color;
	}

	public boolean doesContain(String color) {
		return contains.containsKey(color);
	}
}