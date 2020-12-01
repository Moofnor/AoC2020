package day1;

import utilities.InputLoader;

public class Puzzle1 {
    public Puzzle1() {
        System.out.println("Day 1: Puzzle 1");
        Object[] expenses = InputLoader.asArray(1);
        OUTER:
        for (int i = 0; i < expenses.length - 2; i++ ) {
            for (int j = i + 1; j < (expenses.length - 1); j++) {
                if ((int) expenses[i] + (int) expenses[j] == 2020) {
                    System.out.println("Answer: " + ((int) expenses[i] * (int) expenses[j]));
                    break OUTER;
                }
            }
        }

    }


}
