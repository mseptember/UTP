package zad1;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String home = System.getProperty("user.home");
        String nazwaPliku = home + "/dictionary.txt";
        Dictionary dictionary = new Dictionary(nazwaPliku);

        dictionary.add("granat", "owoc występujący w części Azji Zachodniej, na Kaukazie i na półwyspie Indyjskim");
        dictionary.add("granat", "rodzaj broni, pocisk rażący odłamkami i energią wybuchu albo zapalający");
        dictionary.add("granat", "ciemny odcień barwy niebieskiej");

        dictionary.add("granat", "ciemny odcień barwy niebieskiej"); //test czy mogę dodać to samo

        System.out.println("=========================");

        for (String s : dictionary.lookup("granat")) {
            System.out.println(s);
        }

        System.out.println("=========================");

        dictionary.delete("granat", 3); //test usuwania definicji słowa

        System.out.println("=========================");

        for (String s : dictionary.lookup("granat")) {
            System.out.println(s);
        }

        System.out.println("==========================");

        dictionary.update("granat", "kolor", 1);

        System.out.println("=========================");

        for (String s : dictionary.lookup("granat")) {
            System.out.println(s);
        }

        System.out.println("=========================");

        dictionary.save();
    }
}
