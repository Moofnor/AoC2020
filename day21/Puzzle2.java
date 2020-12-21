package day21;

import utilities.InputLoader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Puzzle2 {
	public Puzzle2() {
		String[] input = InputLoader.asString(21).split("\r\n");
		Map<String, Set<String>> allergens = readIngredients(input);
		String answer = findAllergen(allergens);

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

	public String findAllergen(Map<String, Set<String>> allergens) {
		allergens.remove("all");
		Map<String, String> allergenMap = new HashMap<>();
		while (allergens.size() > 0) {
			String[] keySet = allergens.keySet().toArray(new String[0]);
			for (String allergen: keySet) {
				Set<String> ingredients = allergens.get(allergen);
				if (ingredients.size() == 1) {
					allergenMap.put(allergen, ingredients.toArray(new String[0])[0]);
					allergens.remove(allergen);
					allergens = getNextAllergen(allergens, allergenMap.get(allergen));
				}
			}
		}

		List<String> sortedKeys = new ArrayList<>(allergenMap.keySet());
		Collections.sort(sortedKeys);
		StringBuilder answer = new StringBuilder();
		for (String key: sortedKeys)
			answer.append(allergenMap.get(key)+",");

		return answer.toString();
	}

	private Map<String, Set<String>> getNextAllergen(Map<String, Set<String>> allergens, String ingredient) {
		for (String allergen: allergens.keySet()) {
			Set<String> ingredients = allergens.get(allergen);
			ingredients.remove(ingredient);
			allergens.put(allergen, ingredients);
		}
		return allergens;
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
}


