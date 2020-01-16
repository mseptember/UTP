package zad1;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CountryTableModel extends AbstractTableModel {

    public List<Country> list;
    public String[] columnNames;

    public CountryTableModel(List<Country> list, String[] columnNames) {
        this.list = list;
        this.columnNames = columnNames;
        //TODO list = wszystkie obiekty typu country w pliku countries.txt
        //TODO columnNames = pierwszy wiersz w pliku countries.txt
    }

    //ile wierszy w tablicy
    @Override
    public int getRowCount() {
        return list.size();
    }

    //ile kolumn w tablicy
    @Override
    public int getColumnCount() {
        //TODO - ile nazw z pierwszego wiesza z countries.txt (Name, Capital, Population, Flag, LastMod)
        return columnNames.length;
    }

    //nazwy kolumn
    @Override
    public String getColumnName(int column) {
        //TODO - nazwy z pierwszego wiersza z countries.txt
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //TODO - czy kolumna to kolumna populacji
        if (columnIndex == 2) {
            return true;
        } else {
            return false;
        }
    }

    //wartość elementu (rowIndex, columnIndex)
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Country country = list.get(rowIndex);
        if(columnIndex == 0){
            return country.getNazwa();
        }
        else if(columnIndex == 1){
            return country.getStolica();
        }
        else if(columnIndex == 2){
            return country.getPopulacja();
        }
        return null;
    }

    public void setValueAt(Object val, int rowIndex, int columnIndex){
        Country country = list.get(rowIndex);
        if(columnIndex == 0){
            country.setNazwa((String) val);
        }
        else if(columnIndex == 1){
            country.setStolica((String) val);
        }
        else if(columnIndex == 2){
            country.setPopulacja((Integer)val);
        }
    }
}
