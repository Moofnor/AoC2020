package day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.InputLoader;

import java.util.Arrays;

public class Puzzle1 {
	public Puzzle1() {
		String[] layoutString = InputLoader.asString(11).split("\r\n");
		char[][] layout = new char[layoutString.length][layoutString[0].length()];
		for (int i=0; i < layoutString.length; i++){
			layout[i] = layoutString[i].toCharArray();
		}
		Ferry fe = new Ferry(layout);
		char[][] newState = fe.currentState;
		do {
			fe.currentState = newState;
			newState = fe.getNewState();
		} while (!Arrays.deepEquals(fe.currentState, newState));
		int answer = fe.getOccupancy();
		System.out.println("Answer: " + answer);
	}
}

class Ferry {
	char[][] currentState;
	int nRows, nCols;

	public Ferry(String[] layoutString) {
		char[][] layout = new char[layoutString.length][layoutString[0].length()];
		for (int i=0; i < layoutString.length; i++){
			layout[i] = layoutString[i].toCharArray();
		}
		new Ferry(layout);

	}

	public Ferry(char[][] layout) {
		currentState = layout;
		nRows = layout.length;
		nCols = layout[0].length;
	}

	int getSeatOccupancy(int row, int col) {
		if (isInValidSeat(row, col)) return 0;
		if (currentState[row][col] == '#')
			return 1;
		return 0;
	}

	 char getSeat(int row, int col) {
		 if (isInValidSeat(row, col)) return '.';
		 return currentState[row][col];
	 }

	char[][] getNewState() {
		char[][] newState = new char[nRows][nCols];
		for (int row = 0; row < nRows; row++) {
			for (int col = 0; col < nCols; col++) {
				newState[row][col] = evaluateSeat(row, col);
			}
		}
		return newState;
	}

	char[][] getNewState2() {
		char[][] newState = new char[nRows][nCols];
		for (int row = 0; row < nRows; row++) {
			for (int col = 0; col < nCols; col++) {
				newState[row][col] = evaluateSeat2(row, col);
			}
		}
		return newState;
	}

	int getOccupancy() {
		int occupied = 0;
		for (int row = 0; row < nRows; row++) {
			for (int col = 0; col < nCols; col++) {
				if (currentState[row][col] == '#')
					occupied++;
			}
		}
		return occupied;
	}
	private char evaluateSeat(int row, int col) {
		int sumOccupied =
				getSeatOccupancy(row-1, col-1) +
				getSeatOccupancy(row-1, col) +
				getSeatOccupancy(row-1, col+1) +
				getSeatOccupancy(row, col-1) +
				getSeatOccupancy(row, col+1) +
				getSeatOccupancy(row+1, col-1) +
				getSeatOccupancy(row+1, col) +
				getSeatOccupancy(row+1, col+1);
		switch (getSeat(row, col)) {
			case '#':
				if (sumOccupied >= 4) return 'L';
				else return '#';
			case 'L':
				if (sumOccupied == 0) return '#';
				else return 'L';
			default:
				return '.';
		}
	}

	private char evaluateSeat2(int row, int col) {
		if (getSeat(row, col) == '.')
			return '.';

		int[][] directions = {
				{+1, 0},
				{+1, +1},
				{-1, 0},
				{-1, +1},
				{-1, -1},
				{+1, -1},
				{0, +1},
				{0, -1}
		};
		int sumOccupied = 0;
		int i, iRow, iCol;
		for (int[] dir : directions) {
			i = 1;
			do {
				iRow = row + (i * dir[0]);
				iCol = col + (i * dir[1]);
				if (getSeat(iRow, iCol) == 'L' || getSeat(iRow, iCol) == '#')
					break;
				i++;
			} while (!isInValidSeat(iRow, iCol));
			if (getSeat(iRow, iCol) == '#')
				sumOccupied++;
		}
		switch (getSeat(row, col)) {
			case '#':
				if (sumOccupied >= 5) return 'L';
				else return '#';
			case 'L':
				if (sumOccupied == 0) return '#';
				else return 'L';
			default:
				return '.';
		}
	}

	void changeSeat(int row, int col) {
		if (isInValidSeat(row, col)) return;
		switch (currentState[row][col]) {
			case '#':
				currentState[row][col] = 'L';
				break;
			case 'L':
				currentState[row][col] = '#';
				break;
			default:
				break;
		}
	}

	private boolean isInValidSeat(int row, int col) {
		return ((row >= nRows || row < 0) || (col >= nCols || col < 0));
	}
}

class FerryTest {
	static Ferry fe;

	@BeforeEach
	void setup() {
		char[][] testInput =
				{
						"L.LL.LL.LL".toCharArray(),
						"LLLLLLL.LL".toCharArray(),
						"L.L.L..L..".toCharArray(),
						"LLLL.LL.LL".toCharArray(),
						"L.LL.LL.LL".toCharArray(),
						"L.LLLLL.LL".toCharArray(),
						"..L.L.....".toCharArray(),
						"LLLLLLLLLL".toCharArray(),
						"L.LLLLLL.L".toCharArray(),
						"L.LLLLL.LL".toCharArray()
				};

		fe = new Ferry(testInput);
	}

	@Test
	void emptyAndInvalidSeatReturns0() {
		Assertions.assertEquals(0, fe.getSeatOccupancy(0, 0));
		Assertions.assertEquals(0, fe.getSeatOccupancy(-1, 0));
		Assertions.assertEquals(0, fe.getSeatOccupancy(1, 0));
	}

	@Test
	void occupiedSeatReturns1() {
		fe.changeSeat(0, 0);
		Assertions.assertEquals(1, fe.getSeatOccupancy(0, 0));
	}

	@Test
	void changeFloorDoesNotChange() {
		fe.changeSeat(0, 1);
		Assertions.assertEquals(0, fe.getSeatOccupancy(0, 1));
	}

}