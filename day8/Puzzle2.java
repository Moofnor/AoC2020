package day8;

import utilities.InputLoader;
import java.util.List;

public class Puzzle2 {
	public Puzzle2() {
		String[] commands = InputLoader.asString(8).split("\r\n");
		String[] alteredCommands = new String[commands.length];
		Console hgc = new Console( new boolean[commands.length], commands);
		int finalIndex = 0;
		int nopjmpIndex = -1;
		while(finalIndex < commands.length) {
			nopjmpIndex = hgc.getNextNopJmp(nopjmpIndex);
			alteredCommands = alterCommands(commands, nopjmpIndex);
			hgc.run(alteredCommands);
			finalIndex = hgc.getCurrentIndex();
		}
		System.out.println("Answer: " + hgc.getAccumulator());
	}

	public String[] alterCommands(String[] commands, int index) {
		String[] alteredCommands = new String[commands.length];
		for (int i = 0; i < commands.length; i++) {
			if (i == index) {
				String[] parts = commands[i].split(" ");
				if (parts[0].equals("nop")) {
					alteredCommands[i] = "jmp " + parts[1];
				} else  {
					alteredCommands[i] = "nop " + parts[1];
				}
			} else {
				alteredCommands[i] = commands[i];
			}
		}
		return alteredCommands;
	}
}
