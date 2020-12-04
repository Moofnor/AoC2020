
import day4.*;

public class RunPuzzle {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("Puzzle 1");
        new Puzzle1();
        long stopTime = System.nanoTime();
        System.out.println("Completed in " + (stopTime-startTime)/1000000 + " ms.");

        startTime = System.nanoTime();
        System.out.println("Puzzle 2");
        new Puzzle2();
        stopTime = System.nanoTime();
        System.out.println("Completed in " + (stopTime-startTime)/1000000 + " ms.");
    }
}
