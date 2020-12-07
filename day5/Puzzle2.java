package day5;

;
import utilities.InputLoader;

import java.util.regex.Pattern;


public class Puzzle2 {
	public Puzzle2() {
		String[] input = InputLoader.asString(5).split("\r\n");

		int nRows = 128;
		String[] seating = new String[nRows];
		for (int i = 0; i < nRows; i++) {
			seating[i] = "________";
		}
		for (String pass : input) {
			StringBuilder sb= new StringBuilder(seating[getRow(pass)]);
			sb.setCharAt(getColumn(pass), 'X');
			seating[getRow(pass)] =	sb.toString();
		}
		int seatID = 0;
		for (int i = 0; i < nRows; i++) {
			if (seating[i].matches(".*X_$|^_X.*|.*X_X.*")) {
				seatID = i * 8 + seating[i].indexOf("_");
			}
			System.out.println(seating[i]);
		}

		System.out.println("Answer: " + seatID);
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
