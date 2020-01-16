package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        try {
            FileVisitorMain fvm = new FileVisitorMain(resultFileName);
            Files.walkFileTree(Paths.get(dirName), fvm);
            fvm.copy();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
