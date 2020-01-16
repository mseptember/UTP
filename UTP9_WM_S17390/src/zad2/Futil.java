package zad2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

public class Futil {

    public static void processDir(String dirName, String resultFileName) {
        Predicate<Path> isFile = Files::isRegularFile;
        Predicate<Path> isTxt = p -> p.toString().endsWith(".txt");

        try {
            Files.walk(Paths.get(dirName)).filter(isFile.and(isTxt)).forEach(e -> {
                Futil.copy(e, resultFileName);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void copy(Path file, String resultFileName) {
        BufferedWriter bw = null;
        BufferedReader br = null;

        try {
            FileOutputStream fos = new FileOutputStream(resultFileName, true);
            bw = new BufferedWriter(new OutputStreamWriter(fos, Charset.forName("UTF8")));
            FileInputStream fis = new FileInputStream(file.toString());
            br = new BufferedReader(new InputStreamReader(fis, Charset.forName("Cp1250")));

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            br.close();
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}