package template;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CreateDay {

    public static void main(String[] args) {
        new CreateDay(4);
    }

    public CreateDay(int dayNo) {
        createDir(dayNo);
        getInput(dayNo);
        createFiles(dayNo);
        System.out.println("Creation of files for day "+dayNo+" completed!");
    }

    private void createDir(int dayNo) {
        Path path = Paths.get("src/day" + dayNo);
        if (!Files.exists(path))
            try {
                Files.createDirectory(path);
            }
            catch (IOException e) {
                System.out.println("Error: " + e);
            }
    }

    private void createFiles(int dayNo) {
        List<String> lines;
        for (int i = 1; i <= 2; i++) {
            Path path = Paths.get("src/day" + dayNo + "/Puzzle" + i + ".java");
            if (!Files.exists(path))
                try {
                    lines = Arrays.asList(
                            "package day" + dayNo + ";",
                            "",
                            "import utilities.InputLoader;",
                            "import java.util.List;",
                            "",
                            "public class Puzzle"+i+" {",
                            "\tpublic Puzzle"+i+"() {",
                            "\t}",
                            "}"
                    );
                    Files.write(path, lines);
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                }
        }
    }

    private void getInput(int dayNo) {
        Path path = Paths.get("src/day" + dayNo + "/input.txt");
        if (!Files.exists(path))
            try {
                URL url = new URL("https://adventofcode.com/2020/day/" + dayNo + "/input");
//                ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
//                FileOutputStream fileOutputStream = new FileOutputStream("src/day" + dayNo + "/input.txt");
//                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
    }
}
