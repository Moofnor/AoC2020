package day20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.InputLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Puzzle1 {
	public Puzzle1() {
		String[] input = InputLoader.asString(20).split("\r\n\r\n");
		Image img = new Image(input);

		long answer = img.findCorners();
		System.out.println("Answer = " + answer);
	}
}

class Image {
	Map<Long, Tile> tiles = new HashMap<>();

	public Image(String[] inputTiles) {
		for (String inputTile: inputTiles) {
			String[] tileRows = inputTile.split("\r\n");
			Tile tile = new Tile(tileRows);
			tiles.put(tile.id, tile);
		}
	}

	public long findCorners() {
		Map<String, Long> pairs = new HashMap<>();
		for (Tile tile: tiles.values()) {
			pairs = checkPairs(pairs, tile);
		}
		List<Long> cornerList = new ArrayList<>();
		for (long id: pairs.values()) {
			cornerList.add(id);
		}
		Map<Object, Long> counts = cornerList.stream().collect(groupingBy(n -> n, HashMap::new, counting()));
		counts.values().removeIf(count -> count < 2);
		long answer = 1;
		for (Object c: counts.keySet())
			answer *= Long.parseLong(c.toString());
		return answer;
	}

	private Map<String, Long> checkPairs(Map<String, Long> pairs, Tile tile) {
		if (pairs.containsKey(tile.bottom) || pairs.containsKey(new StringBuilder().append(tile.bottom).reverse().toString())) {
			pairs.remove(tile.bottom);
			pairs.remove(new StringBuilder().append(tile.bottom).reverse().toString());
		} else {
			pairs.put(tile.bottom, tile.id);
		}
		if (pairs.containsKey(tile.top) || pairs.containsKey(new StringBuilder().append(tile.top).reverse().toString())) {
			pairs.remove(tile.top);
			pairs.remove(new StringBuilder().append(tile.top).reverse().toString());
		} else {
			pairs.put(tile.top, tile.id);
		}
		if (pairs.containsKey(tile.left) || pairs.containsKey(new StringBuilder().append(tile.left).reverse().toString())) {
			pairs.remove(tile.left);
			pairs.remove(new StringBuilder().append(tile.left).reverse().toString());
		} else {
			pairs.put(tile.left, tile.id);
		}
		if (pairs.containsKey(tile.right) || pairs.containsKey(new StringBuilder().append(tile.right).reverse().toString())) {
			pairs.remove(tile.right);
			pairs.remove(new StringBuilder().append(tile.right).reverse().toString());
		} else {
			pairs.put(tile.right, tile.id);
		}
		return pairs;
	}
}

class Tile {
	char[][] img;
	long id;
	String top, bottom, left, right;
	public Tile(String[] tileRows){
		String idStr = tileRows[0].split(" ")[1];
		id = Long.parseLong(idStr.substring(0, idStr.length()-1));
		img = new char[tileRows.length-1][tileRows[0].length()];
		char[] cleft = new char[tileRows[0].length()];
		char[] cright = new char[tileRows[0].length()];
		for (int i = 1; i < tileRows.length; i++) {
			img[i - 1] = tileRows[i].toCharArray();
			cleft[i - 1] = img[i-1][0];
			cright[i - 1] = img[i-1][tileRows[0].length()-1];
		}
		top = new String(img[0]);
		bottom = new String(img[tileRows.length-2]);
		left = new String(cleft);
		right = new String(cright);
	}
}

class Test1 {
	static Image img;

	@BeforeAll
	static void setup() {
		String[] input = InputLoader.sample(20).split("\r\n\r\n");
		img = new Image(input);
	}

	@Test
	void cornerShouldReturn() {
		long corners = img.findCorners();
		Assertions.assertEquals(20899048083289L, corners);
	}
}