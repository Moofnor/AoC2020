package day3;

import utilities.InputLoader;

import java.util.List;

public class Puzzle1 {
    public Puzzle1() {
        List<String> input = InputLoader.asList(3);
        char[][] matrix = getMatrix(input);

        int trees = hitTrees(matrix, 3, 1);

        System.out.println("Answer: " + trees);
    }

    int hitTrees(char[][] matrix, int slopeX, int slopeY) {
        int trees = 0, x = 0, y = 0;
        while (y < matrix.length) {
            if (matrix[y][x % matrix[0].length] == '#')
                trees++;
            x += slopeX;
            y += slopeY;
        }
        return trees;
    }


    char[][] getMatrix(List<String> input) {
        char[][] matrix = new char[input.size()][input.get(0).length()];
        for (int i = 0; i < matrix.length; i++) {
            String row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                matrix[i][j] = row.charAt(j);
            }
        }
        return matrix;
    }
}
