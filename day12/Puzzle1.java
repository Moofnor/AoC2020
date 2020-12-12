package day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.InputLoader;
import java.util.List;

public class Puzzle1 {
	public Puzzle1() {
		String[] input = InputLoader.asString(12).split("\r\n");
		PA pa = new PA(input);
		pa.run();
		System.out.println("Answer: " + pa.getManhattan());
	}
}

class PA {
	String[] instructions;
	int dirAngle = 90;
	String[] directions = {"N", "E", "S", "W"};
	int[] location = {0, 0};

	public PA(String[] sampleInput) {
		instructions = sampleInput;
	}

	void run() {
		for (int i =0; i < instructions.length; i++) {
			processInstruction(instructions[i]);
		}
	}

	void processInstruction(String instruction) {
		switch(instruction.substring(0, 1)) {
			case "N":
				location[1] += Integer.parseInt(instruction.substring(1));
				break;
			case "E":
				location[0] += Integer.parseInt(instruction.substring(1));
				break;
			case "S":
				location[1] -= Integer.parseInt(instruction.substring(1));
				break;
			case "W":
				location[0] -= Integer.parseInt(instruction.substring(1));
				break;
			case "L":
				changeDirection(-Integer.parseInt(instruction.substring(1)));
				break;
			case "R":
				changeDirection(Integer.parseInt(instruction.substring(1)));
				break;
			case "F":
				processInstruction(directions[dirAngle/90]+instruction.substring(1));
				break;
		}
	}

	int getManhattan() {
		return Math.abs(location[0]) + Math.abs(location[1]);
	}

	void changeDirection(int dAngle) {
		dirAngle = (dirAngle + dAngle) % 360;
		if (dirAngle < 0) {
			dirAngle += 360;
		}
	}
}

class ClassTest {
	static PA cl;

	@BeforeEach
	void setup() {
		String[] sampleInput = {
				"F10", "N3", "F7", "R90", "F11"
		};
		cl = new PA(sampleInput);
	}

	@Test
	void f10moves10east() {
		cl.processInstruction("F10");
		Assertions.assertEquals(10, cl.location[0]);
	}

	@Test
	void samplereturns25() {
		cl.run();
		Assertions.assertEquals(25, cl.getManhattan());
	}
}