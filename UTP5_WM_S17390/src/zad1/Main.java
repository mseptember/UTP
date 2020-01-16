/**
 *
 *  @author Wrzesień Maciej S17390
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */
	  
	Function <String, List<String>> flines = e -> {
        List<String> list = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(e));
            String wiersz;

            while((wiersz = br.readLine()) != null){
                list.add(wiersz);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return list;
	};
	
	Function <List<String>, String> join = e -> {
        String s = "";

        for (String k:e) {
            s += k+ " ";
        }

        return s;
	};
	
	Function <String, List<Integer>> collectInts = e -> {
        List <Integer> list = new ArrayList<>();

        Matcher m = Pattern.compile("-?\\d+").matcher(e);

        while (m.find()){
            list.add(Integer.parseInt(m.group()));
        }

        return list;
	};
	
	Function <List<Integer>, Integer> sum = e -> {
        int x = 0;

        for (int k:e) {
            x += k;
        }

        return x;
	};

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}