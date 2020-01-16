package zad1;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Dictionary {
    HashMap<String, List<String>> def = new HashMap<>();
    String nazwaPliku;

    public Dictionary(String nazwaPliku) throws FileNotFoundException {
        this.nazwaPliku = nazwaPliku;

        try {
            FileReader fr = new FileReader(nazwaPliku);

            BufferedReader br = new BufferedReader(fr);
            String wiersz;

            while ((wiersz = br.readLine()) != null) {
                if (Pattern.compile(".+\\s+[=]\\s+.+").matcher(wiersz).find()) { // index. HASŁO = DEFINICJA.
                    String[] tab = wiersz.split("=");

                    if (def.containsKey(tab[0].toLowerCase().trim())) {
                        if (!czyNaLiscie(def.get(tab[0].toLowerCase().trim()), tab[1].substring(1, tab[1].length() - 1))) {
                            def.get(tab[0].toLowerCase().trim()).add(tab[1].substring(1, tab[1].length() - 1));
                        }
                    } else {
                        List<String> l = new ArrayList<>();
                        l.add(tab[1].substring(1, tab[1].length() - 1));
                        def.put(tab[0].toLowerCase().trim(), l);
                    }
                }
            }
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> lookup(String haslo) {
        List<String> list = null;

        try {
            list = def.get(haslo.toLowerCase());

            list.sort((a, b) -> {
                if (a.compareToIgnoreCase(b) < 0) {
                    return -1;
                } else if (a.compareToIgnoreCase(b) > 0) {
                    return 1;
                }
                else {
                    return 0;
                }
            });

            int k = 1;

            //System.out.println(haslo);
            for (String s : list) {
                if(!s.substring(0,4).contains(".")){
                    list.set(k-1, k + "." + list.get(k-1));
                }
                else {
                    list.set(k-1, k + "." + kropka(list.get(k-1)));
                }
                k++;
            }

        } catch (NullPointerException ex) {
            System.out.println("Nie ma takiego hasła" + haslo);
        }

        return list;
    }

    private String kropka(String s) {
        int k = 0;

        for (char c : s.toCharArray()) {
            if (k < s.length() - 1) {
                if (c == '.') {
                    return s.substring(k + 1);
                }
            }
            k++;
        }

        return s;
    }

    public void add(String haslo, String definicja) {
        try {
            List<String> list = def.get(haslo.toLowerCase());
            if (!czyNaLiscie(list, definicja)) { //czy jest w słowniku takie hasło z taką definicją
                list.add(definicja);
                System.out.println("Dodano definicję słowa \"" + haslo + "\" do słownika");
            }
            else {
                System.out.println("Taka definicja już istnieje!");
            }
        } catch (NullPointerException ex) {
            List<String> stringList = new ArrayList<>();
            stringList.add(definicja);
            def.put(haslo, stringList);
            System.out.println("Dodano słowo \"" + haslo + "\" do słownika");
        }
    }

    private boolean czyNaLiscie(List<String> list, String definicja) {
        for (String s : list) {
            if (definicja.equalsIgnoreCase(kropka(s))) {
                return true;
            }
        }
        return false;
    }

    public void delete(String haslo, int index) {
        List<String> list = lookup(haslo);
        list.remove(index - 1);
        System.out.println("Usunięto definicję słowa \"" + haslo + "\" ze słownika");
    }

    public void update(String haslo, String definicja, int index) {
        List<String> list = lookup(haslo);
        list.remove(index - 1);

        list.add(definicja);
        lookup(haslo);
        System.out.println("Zaktualizowano słownik");
    }

    public void save() {
        try {
            File plik = new File(nazwaPliku);
            FileWriter zapisPliku = new FileWriter(plik, false);

            for (String w : def.keySet()) {
                for (String k : def.get(w)) {
                    zapisPliku.write(w + " = " + kropka(k) + "\n");
                }
            }
            zapisPliku.close();
            System.out.println("ZAPISANO");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
