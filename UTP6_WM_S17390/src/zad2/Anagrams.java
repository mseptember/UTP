/**
 *
 *  @author Wrzesień Maciej S17390
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Anagrams {

    public List<String> aw;
    public List<List<String>> wtf;

    public Anagrams(String allWords) throws FileNotFoundException {
        this.aw = new ArrayList<String>();
        this.wtf = new ArrayList<List<String>>();

        try {
            FileReader fr = new FileReader(allWords);

            BufferedReader br = new BufferedReader(fr);
            String wiersz;

            while ((wiersz = br.readLine()) != null) {
                String[] tab = wiersz.split("\\s+");
                for (int i=0; i<tab.length; i++) {
                    aw.add(tab[i]);
                }
            }
            br.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<List<String>> getSortedByAnQty() {
        List<List<String>> wynik = new ArrayList<List<String>>();
        List<String> znalezione = new ArrayList<String>();
        List<String> tmpAw = new ArrayList<String>(aw);

        for (String s : aw) {
            if(tmpAw.contains(s)) {
                znalezione.add(s);
                for (int i = aw.indexOf(s)+1; i<aw.size(); i++) {
                    if(s.length() == aw.get(i).length() && czyToAnagram(s, aw.get(i))) {
                        znalezione.add(aw.get(i));
                        tmpAw.remove(aw.get(i));
                    }
                }
                wynik.add(new ArrayList<String>(znalezione));
                znalezione.clear();
            }
        }

        wynik.sort((o1, o2) -> {
            if(o1.size() > o2.size()) {
                return -1;
            }
            else if(o1.size() < o2.size()) {
                return 1;
            }
            else {
                return o1.get(0).compareTo(o2.get(0));
            }
        });
        wtf = new ArrayList<List<String>>(wynik);

        return wynik;
    }

    public String getAnagramsFor(String word) {
        String wynik = word + ": ";

        for (int i=0; i<wtf.size(); i++) {
            for (int j=0; j<wtf.get(i).size(); j++) {
                if((wtf.get(i)).get(j).equals(word)) {
                    (wtf.get(i)).remove(word);
                    wynik += wtf.get(i);
                }
            }
        }

        if (wynik.equals(word + ": ")) {
            wynik += "null";
        }

        return wynik;
    }

    public boolean czyToAnagram(String s1, String s2) {
        for (int i=0; i<s1.length(); i++) {
            if(!s2.contains(Character.toString(s1.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
