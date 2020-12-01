package utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputLoader {
    private static Scanner sc;
    private static File file;

    public static Object[] asArray(int dayNo) {
        List<Integer> input = new ArrayList<>();
        file = new File("src/day" + dayNo + "/input.txt");

        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                input.add(Integer.parseInt(sc.nextLine()));
            }

        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return input.toArray();
    }
}
