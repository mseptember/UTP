package zad1;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class CountryTable {
    public String fileName;
    public CountryTableModel tableModel;

    public CountryTable(String fileName) throws FileNotFoundException {
        this.fileName = fileName;

        List<Country> countries = dodajPanstwa(fileName);
        String[] columnNames = dodajNazwyKolumn(fileName);
        this.tableModel = new CountryTableModel(countries, columnNames);
    }

    public List<Country> dodajPanstwa(String fileName){
        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String napis;
            StringBuffer sb = new StringBuffer();
            int licznikPanstw = 0;

            while((napis = br.readLine()) != null){
                sb.append(napis + "\n");
                licznikPanstw += 1;
            }

            br.close();

            Country[] panstwa = new Country[licznikPanstw];
            napis = sb.toString();
            Scanner sc = new Scanner(napis).useDelimiter("\\t");
            for(int i = 0; i<licznikPanstw; i++){
                String nazwa;
                String stolica;
                Integer populacja;

                if(sc.hasNext()) {
                    nazwa = sc.next();
                } else {
                    nazwa = "---";
                }

                if(sc.hasNext()) {
                    stolica = sc.next();
                } else {
                    stolica = "---";
                }

                if(sc.hasNextInt()) {
                    populacja = sc.nextInt();
                } else {
                    populacja = 0;
                }

                panstwa[i] = new Country(nazwa, stolica, populacja);
            }
            sc.close();

            List<Country> countries = Arrays.asList(panstwa);
            return countries;

        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public String[] dodajNazwyKolumn(String fileName){
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String napis;
            napis = br.readLine();

            br.close();

            Scanner scan = new Scanner(napis);
            int licznik = 0;
            while (scan.hasNext()) {
                scan.next();
                licznik += 1;
            }
            scan.close();

            scan = new Scanner(napis);
            String[] kolumny = new String[licznik];
            for (int i = 0; i < licznik; i++) {
                if (scan.hasNext()) {
                    kolumny[i] = scan.next();
                }
            }
            scan.close();

            return kolumny;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JTable create() {
        //TODO - ma zwracać TABELĘ!
        JTable jt = new JTable(tableModel);
        jt.getColumnModel().getColumn(2).setCellRenderer(new PopulationRenderer());
        jt.setRowHeight(40);
        return jt;
    }
}