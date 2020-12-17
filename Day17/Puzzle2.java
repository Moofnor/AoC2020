package day17;

import utilities.InputLoader;

import java.util.Arrays;
import java.util.List;

public class Puzzle2 {
	public Puzzle2() {
		String[] input = InputLoader.asString(17).split("\r\n");
		Cubes2 c = new Cubes2(input, 6);
		c.run();
		int answer = c.getAllActive();
		System.out.println("Answer: " + answer);
	}
}
// # active, . inactive
class Cubes2 {
	int[][][][] state;
	int offset, cycle = 0;

	public Cubes2(String[] input, int maxCycles) {
		int maxSize = input.length + 2 * maxCycles + 2;
		offset = maxCycles + 1;

		state = new int[2 * offset + 1][2 * offset + 1][maxSize][maxSize];
		parseInput(input);
	}

	public void run() {
		int sumActive;
		while (cycle < offset-1) {
			int[][][][] newState = new int[state.length][state[0].length][state[0][0].length][state[0][0][0].length];
			for (int w = 1; w < state.length - 1; w++) {
				for (int z = 1; z < state[0].length - 1; z++) {
					for (int x = 1; x < state[0][0].length - 1; x++) {
						for (int y = 1; y < state[0][0][0].length - 1; y++) {
							sumActive = getActive(w, z, x, y);
							if (state[w][z][x][y] == 1)
								if (sumActive == 2 || sumActive == 3)
									newState[w][z][x][y] = 1;
								else
									newState[w][z][x][y] = 0;
							else if (sumActive == 3)
								newState[w][z][x][y] = 1;
							else
								newState[w][z][x][y] = 0;
						}
					}
				}
			}
			state = newState;
			int allActive = getAllActive();
			cycle++;
		}

	}
	public int getAllActive() {
		int sum = 0;
		for (int w = 0; w < state.length; w++) {
			for (int z = 0; z < state[0].length; z++) {
				for (int x = 0; x < state[0][0].length; x++) {
					for (int y = 0; y < state[0][0][0].length; y++) {
						sum += state[w][z][x][y];
					}
				}
			}
		}
		return sum;
	}

	private int getActive(int w, int z, int x, int y) {
		int[] parts = {-1, 0, 1};
		int sum = 0;
		for (int dw: parts)
			for (int dz: parts)
				for (int dx: parts)
					for (int dy: parts) {
						if (dw == 0 && dz == 0 && dx == 0 && dy == 0)
							continue;
						sum += state[w + dw][z + dz][x + dx][y + dy];
					}
		return sum;
	}


	private void parseInput(String[] input) {
		for (int r = 0; r < input.length; r++) {
//			state[offset][r+offset] = Arrays.stream(input[r].replace("#", "1").replace(".", "0").split("")).mapToInt(Integer::parseInt).toArray();
			for (int c = 0; c < input[r].length(); c++) {
				if (input[r].charAt(c) == '#')
					state[offset][offset][r+offset][c+offset] = 1;
				else
					state[offset][offset][r+offset][c+offset] = 0;
			}
		}

	}
}

