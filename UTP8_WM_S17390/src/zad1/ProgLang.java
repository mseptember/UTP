package zad1;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang {
    Map<String, Set<String>> mapaProgramistow = new LinkedHashMap<String, Set<String>>();
    Map<String, Set<String>> mapaJezykow = new LinkedHashMap<String, Set<String>>();
    String nazwaPliku;

    public ProgLang(String nazwaPliku) {
        this.nazwaPliku = nazwaPliku;
        try {
            Scanner scan = new Scanner(new File(nazwaPliku));

            while (scan.hasNextLine()) {
                String str = scan.nextLine();
                String[] tab = str.split("\\t");
                String lang = tab[0];
                Set<String> tmp = new LinkedHashSet<>();

                for (int i = 1; i < tab.length; i++) {
                    if (!(tmp.contains(tab[i]))) {
                        tmp.add(tab[i]);
                    }
                }

                mapaProgramistow.put(lang, tmp);
            }
            scan.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public Map<String, Set<String>> getLangsMap() {
        return mapaJezykow;
    }

    public Map<String, Set<String>> getProgsMap() {
        return mapaProgramistow;
    }

    public Map<String, Set<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(mapaJezykow, (o1, o2) -> o2.getValue().size() - o1.getValue().size());
    }

    public Map<String, Set<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(mapaProgramistow, (o1, o2) -> {
            int tmp = o2.getValue().size() - o1.getValue().size();

            if(tmp != 0) {
                return tmp;
            }
            else {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
    }

    public Map<String, Set<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        return filtered(mapaProgramistow, e -> e.getValue().size() > n);
    }

    public <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        Map<K, V> wynik = new LinkedHashMap<>();
        List<Map.Entry<K, V>> listaSort = new ArrayList<>(map.entrySet());

        listaSort.sort(comparator);

        for(Map.Entry<K, V> e : listaSort) {
            wynik.put(e.getKey(), e.getValue());
        }

        return wynik;
    }

    public <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
        Map<K, V> wynik = new LinkedHashMap<>();
        List<Map.Entry<K, V>> listaSort = new ArrayList<>(map.entrySet());

        for(Map.Entry<K, V> e : listaSort) {
            if(predicate.test(e))
                wynik.put(e.getKey(), e.getValue());
        }

        return wynik;
    }
}

//sorted() - mapa i comparator (Map<K, V>, Comparator<Map.Entry<K,V>>)
//filtered() - mapa i predicate (Map<K,V>, Predicate<Map.Entry<K,V>>)