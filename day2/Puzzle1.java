package day2;

import utilities.InputLoader;

import java.util.List;

public class Puzzle1 {
    public Puzzle1() {
        List<String> input = InputLoader.asList(2);

        String[] splitInput;
        String password;
        char filterChar;
        int max, min, valid = 0, count;

        for (int i = 0; i < input.size(); i++) {
            splitInput = input.get(i).split(":");
            filterChar = splitInput[0].charAt(splitInput[0].length()-1);
            min = Integer.parseInt(splitInput[0].split("-")[0]);
            max = Integer.parseInt(splitInput[0].split("-")[1].substring(0,2).strip());

            password = splitInput[1].strip();
            count = 0;
            for (int j=0; j < password.length(); j++) {
                if (password.charAt(j) == filterChar)
                    count++;
            }
            if (count >= min && count <= max)
                valid++;
        }
        System.out.println("Answer: " + valid);
    }
}
