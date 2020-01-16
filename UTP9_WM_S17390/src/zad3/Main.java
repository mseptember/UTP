/**
 * @author Wrzesień Maciej S17390
 */

package zad3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Map<String, List<String>> map;

        try {
            map = new BufferedReader(new InputStreamReader(new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").
                    openStream())).lines().collect(Collectors.groupingBy(e -> {
                char[] tab = e.toCharArray();
                Arrays.sort(tab);
                return new String(tab);
            }));

            int najdluzszy = map.entrySet().stream().
                    max((e1, e2) -> Integer.compare(e1.getValue().size(), e2.getValue().size())).
                    get().getValue().size();

            map.entrySet().stream()
                    .filter(f -> f.getValue().size() == najdluzszy)
                    .forEach(g -> System.out.println(g.getKey().join(" ", g.getValue())));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
