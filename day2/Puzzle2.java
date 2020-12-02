package day2;

import utilities.InputLoader;

import java.util.List;

public class Puzzle2 {
    public Puzzle2() {
        List<String> input = InputLoader.asList(2);

        String[] splitInput;
        String password;
        char filterChar;
        int max, min, valid = 0;

        for (int i = 0; i < input.size(); i++) {
            splitInput = input.get(i).split(":");
            filterChar = splitInput[0].charAt(splitInput[0].length()-1);
            min = Integer.parseInt(splitInput[0].split("-")[0]);
            max = Integer.parseInt(splitInput[0].split("-")[1].substring(0,2).strip());

            password = splitInput[1].strip();
            if (password.charAt(min-1) == filterChar ^ password.charAt(max-1) == filterChar)
                valid++;
        }
        System.out.println("Answer: " + valid);
    }
}
