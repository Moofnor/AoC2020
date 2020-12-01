package day1;

import utilities.InputLoader;

public class Puzzle2 {
    public Puzzle2() {
        System.out.println("Day 1: Puzzle 2");
        Object[] expenses = InputLoader.asArray(1);
        OUTER:
        for (int i = 0; i < expenses.length - 3; i++) {
            for (int j = i + 1; j < (expenses.length - 2); j++) {
                for (int k = j + 1; k < (expenses.length - 1); k++) {
                    if ((int) expenses[i] + (int) expenses[j] + (int) expenses[k]  == 2020) {
                        System.out.println("Answer: " + ((int) expenses[i] * (int) expenses[j] * (int) expenses[k]));
                        break OUTER;
                    }
                }
            }
        }
    }
}
