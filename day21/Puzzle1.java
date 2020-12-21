package day21;

import utilities.InputLoader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Puzzle1 {
	public Puzzle1() {
		String[] input = InputLoader.asString(21).split("\r\n");
		Map<String, Set<String>> allergens = readIngredients(input);
		long answer = countNonAllergen(allergens, input);

		System.out.println("Answer: " + answer);
	}

	public Map<String, Set<String>> readIngredients(String[] input) {
		Pattern pattern = Pattern.compile("(?<ingredients>([a-z]+ )+)\\((?:contains )(?<allergens>([a-z]+,? ?)+)");
		Matcher matcher;

		Map<String, Set<String>> allergensMap = new HashMap<>();
		allergensMap.put("all", new HashSet<>());

		for (String row: input) {
			matcher = pattern.matcher(row);
			while (matcher.find()) {
				String[] allergens = matcher.group("allergens").split(", ");
				Set<String> ingredients = new HashSet<>(Arrays.asList(matcher.group("ingredients").split(" ")));
				allergensMap.put("all", addIngredients(allergensMap.get("all"), ingredients));
				for (String allergen: allergens) {
					if (allergensMap.containsKey(allergen)) {
						allergensMap.put(allergen, findMatching(allergensMap.get(allergen), ingredients));
					} else {
						allergensMap.put(allergen, ingredients);
					}
				}
			}
		}
		return allergensMap;
	}

	public long countNonAllergen(Map<String, Set<String>> allergens, String[] input) {
		long count = 0;
		String[] nonAllergens = findNonAllergen(allergens);
		List<String> nonA = Arrays.asList(nonAllergens);
		Collections.sort(nonA);
		for (String nonAllergen: nonAllergens)
			for (String meal: input)
				if (meal.contains(" "+nonAllergen+" ") || meal.startsWith(nonAllergen+" "))
					count++;
		return count;
	}

	private String[] findNonAllergen(Map<String, Set<String>> allergens) {
		for (String allergen: allergens.keySet()) {
			if (allergen.equals("all"))
				continue;
			allergens.put("all", subtractIngredients(allergens.get("all"), allergens.get(allergen)));
		}
		return allergens.get("all").toArray(new String[0]);
	}

	private Set<String> findMatching(Set<String> ingredients1, Set<String> ingredients2) {
		HashSet<String> s = new HashSet<>(ingredients1);
		s.retainAll(ingredients2);
		return s;
	}

	private Set<String> addIngredients(Set<String> ingredients1, Set<String> ingredients2) {
		HashSet<String> s = new HashSet<>(ingredients1);
		s.addAll(ingredients2);
		return s;
	}

	private Set<String> subtractIngredients(Set<String> ingredients1, Set<String> ingredients2) {
		HashSet<String> s = new HashSet<>(ingredients1);
		s.removeAll(ingredients2);
		return s;
	}
}


