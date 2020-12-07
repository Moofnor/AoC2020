package day7;

import utilities.InputLoader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle2 {

	public Puzzle2() {
		String[] input = InputLoader.asString(7).split("\r\n");
		HashMap<String, Bag> bags = new HashMap<>();
		Bag bag;
		for (String bagString: input) {
			bag = new Bag(bagString.split(" contain "));
			bags.put(bag.getColor(), bag);
		}
		int answer = getNumberOfBags(1, "shiny gold", bags) - 1;
		System.out.println("Answer: " + answer);
	}

	public int getNumberOfBags(int amount, String color, HashMap<String, Bag> bags) {
		if (bags.get(color).contains.keySet().size() == 0) {
			return amount;
		}
		int subAmount = amount;
		for (String containingBagColor : bags.get(color).contains.keySet()) {
			subAmount += amount * getNumberOfBags(bags.get(color).contains.get(containingBagColor), containingBagColor, bags);
		}
		return subAmount;
	}
}
