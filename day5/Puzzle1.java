package day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.InputLoader;


public class Puzzle1 {
	public Puzzle1() {
		String[] input = InputLoader.asString(5).split("\r\n");
		int maxID = 0;

		for (String pass : input) {
			int seatID = getSeatID(pass);
			if (seatID > maxID) {
				maxID = seatID;
			}
		}
		System.out.println("Answer: " + maxID);
	}
	int getRow(String pass) {
		String rowBinary = pass.substring(0, 7).replace("F", "0").replace("B", "1");
		int row = Integer.valueOf(rowBinary, 2);
		return row;
	}

	int getColumn(String pass) {
		String colBinary = pass.substring(7).replace("L", "0").replace("R", "1");
		int col = Integer.valueOf(colBinary, 2);
		return col;
	}

	int getSeatID(String pass) {
		return getRow(pass) * 8 + getColumn(pass);
	}

}

class Testing {
	static Puzzle1 pz;
	@BeforeAll
	static void setup() {
		pz = new Puzzle1();
	}
	@Test
	void getRowTest() {
		Assertions.assertEquals(44, pz.getRow("FBFBBFFRLR"));
	}

	@Test
	void getColTest() {
		Assertions.assertEquals(5, pz.getColumn("FBFBBFFRLR"));
	}

	@Test
	void getSeatID() {
		Assertions.assertEquals(357, pz.getSeatID("FBFBBFFRLR"));
	}

}