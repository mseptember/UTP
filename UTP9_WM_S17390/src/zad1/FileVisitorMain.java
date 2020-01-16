package zad1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileVisitorMain implements FileVisitor<Path> {
    PathMatcher pm;
    BufferedWriter bw = null;
    FileOutputStream fos = null;
    BufferedReader br = null;
    FileInputStream fis = null;
    List<Path> list;
    Charset charin = Charset.forName("Cp1250"), charout = Charset.forName("UTF8");

    public FileVisitorMain(String result) throws IOException {
        this.pm = FileSystems.getDefault().getPathMatcher("glob:*.txt");
        this.fos = new FileOutputStream(result);
        this.list = new ArrayList<>();
    }

    public void copy() throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(fos, charout));

        for(Path f : list) {
            fis = new FileInputStream(f.toString());
            br = new BufferedReader(new InputStreamReader(fis, charin));
            String wiersz;

            while((wiersz = br.readLine()) != null) {
                bw.write(wiersz);
                bw.newLine();
                bw.flush();
            }

            br.close();
        }
        bw.close();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile() && pm.matches(file.getFileName())) {
            list.add(file);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}
