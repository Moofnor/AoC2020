package day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.InputLoader;
import java.util.List;

public class Puzzle2 {
	public Puzzle2() {
		String[] input = InputLoader.asString(12).split("\r\n");
		WP pa = new WP(input);
		pa.run();
		System.out.println("Answer: " + pa.getManhattan());
	}
}

class WP {
	String[] instructions;
	int[] location = {0, 0};
	int[] waypoint = {10, 1};

	public WP(String[] sampleInput) {
		instructions = sampleInput;
	}

	void run() {
		for (int i =0; i < instructions.length; i++) {
			processInstruction(instructions[i]);
		}
	}

	void processInstruction(String instruction) {
		String dir = instruction.substring(0, 1);
		int value = Integer.parseInt(instruction.substring(1));


		switch(dir) {
			case "N":
				waypoint[1] += Integer.parseInt(instruction.substring(1));
				break;
			case "E":
				waypoint[0] += Integer.parseInt(instruction.substring(1));
				break;
			case "S":
				waypoint[1] -= Integer.parseInt(instruction.substring(1));
				break;
			case "W":
				waypoint[0] -= Integer.parseInt(instruction.substring(1));
				break;
			case "L":
				changeDirection(-Integer.parseInt(instruction.substring(1)));
				break;
			case "R":
				changeDirection(Integer.parseInt(instruction.substring(1)));
				break;
			case "F":
				location[0] += value * waypoint[0];
				location[1] += value * waypoint[1];
				break;
		}
	}

	int getManhattan() {
		return Math.abs(location[0]) + Math.abs(location[1]);
	}

	void changeDirection(int dAngle) {
		int nE = (int) Math.round(Math.cos(dAngle*(Math.PI/180)) * waypoint[0] + Math.sin(dAngle*(Math.PI/180)) * waypoint[1]);
		int nN = (int) Math.round(Math.cos(dAngle*(Math.PI/180)) * waypoint[1] - Math.sin(dAngle*(Math.PI/180)) * waypoint[0]);
		waypoint[0] = nE;
		waypoint[1] = nN;
	}
}

class WPTest {
	static WP cl;

	@BeforeEach
	void setup() {
		String[] sampleInput = {
				"F10", "N3", "F7", "R90", "F11"
		};
		cl = new WP(sampleInput);
	}

	@Test
	void f10moves100east10North() {
		cl.processInstruction("F10");
		Assertions.assertArrayEquals(new int[]{100, 10}, cl.location);
	}

	@Test
	void sampleReturns286() {
		cl.run();
		Assertions.assertEquals(286, cl.getManhattan());
	}

	@Test
	void rotatioTest() {
		cl.processInstruction("L90");
		Assertions.assertArrayEquals(new int[]{-1, 10}, cl.waypoint);
		cl.processInstruction("L90");
		Assertions.assertArrayEquals(new int[]{-10, -1}, cl.waypoint);
		cl.processInstruction("L90");
		Assertions.assertArrayEquals(new int[]{1, -10}, cl.waypoint);
		cl.processInstruction("L90");
		Assertions.assertArrayEquals(new int[]{10, 1}, cl.waypoint);
	}
}