package day8;

import utilities.InputLoader;


public class Puzzle1 {
	public Puzzle1() {
		String[] commands = InputLoader.asString(8).split("\r\n");
		Console hgc = new Console( new boolean[commands.length], commands);
		hgc.run();
		System.out.println("Answer: " + hgc.getAccumulator());
	}
}

class Console {
	private boolean[] visited;
	private String[] commands;
	private int currentIndex = 0, accumulator = 0;

	public Console(boolean[] visited, String[] commands) {
		this.commands = commands;
		this.visited = visited;
	}

	public void run() {
		int returnValue;
		while(currentIndex < visited.length && !visited[currentIndex]) {
			returnValue = processCommand(commands[currentIndex].split(" "));
			visited[currentIndex] = true;
			currentIndex += returnValue;
		}
	}

	public void run(String[] commands) {
		currentIndex = 0;
		accumulator = 0;
		visited = new boolean[commands.length];
		int returnValue;
		while(currentIndex < visited.length && !visited[currentIndex]) {
			returnValue = processCommand(commands[currentIndex].split(" "));
			visited[currentIndex] = true;
			currentIndex += returnValue;
		}
	}

	private int processCommand(String[] command) {
		int value = Integer.parseInt(command[1]);
		switch (command[0]) {
			case "acc":
				accumulator += value;
			case "nop":
				return 1;
			case "jmp":
				return value;
			default:
				System.out.println("Something is wrong");
				return 0;
		}
	}

	public int getNextNopJmp(int start) {
		int searchIndex = start + 1;
		while (searchIndex < commands.length) {
			if (commands[searchIndex].startsWith("nop") || commands[searchIndex].startsWith("jmp")) {
				return searchIndex;
			}
			searchIndex++;
		}
		System.out.println("Out of options");
		return searchIndex;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public int getAccumulator() {
		return accumulator;
	}
}