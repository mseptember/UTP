/**
 *
 *  @author Wrzesień Maciej S17390
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomersPurchaseSortFind {
    public List<Purchase> list = new ArrayList<Purchase>();

    public void readFile(String fname) {
        try {
            FileReader fr = new FileReader(fname);

            BufferedReader br = new BufferedReader(fr);
            String wiersz;

            while ((wiersz = br.readLine()) != null) {
                String[] tab = wiersz.split(";");
                list.add(new Purchase(tab[0], tab[1].split(" ")[0], tab[1].split(" ")[1], tab[2],
                        Double.parseDouble(tab[3]), Double.parseDouble(tab[4])));
            }
            br.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showSortedBy(String data) {
        List<Purchase> tmpList = new ArrayList<Purchase>(list);

        if(data.equals("Nazwiska")) {
            tmpList.sort((o1, o2) -> {
                if(o1.getNazwisko().compareTo(o2.getNazwisko()) != 0) {
                    return o1.getNazwisko().compareTo(o2.getNazwisko());
                }
                else {
                    return o1.getIdKlienta().compareTo(o2.getIdKlienta());
                }
            });
            System.out.println(data);

            for (Purchase p : tmpList) {
                System.out.println(p.toString());
            }
            System.out.println();
        }

        else if(data.equals("Koszty")) {
            tmpList.sort((o1, o2) -> {
                if(o2.getKoszty().compareTo(o1.getKoszty()) != 0) {
                    return o2.getKoszty().compareTo(o1.getKoszty());
                }
                else {
                    return o1.getIdKlienta().compareTo(o2.getIdKlienta());
                }
            });
            System.out.println(data);

            for (Purchase p : tmpList) {
                System.out.println(p.toString() + " (koszt: " + p.getKoszty() + ")");
            }
            System.out.println();
        }
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        for (Purchase p:list) {
            if(p.idKlienta.equals(id)){
                System.out.println(p);
            }
        }
        System.out.println();
    }
}
