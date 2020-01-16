package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PopulationRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        Component fontChange = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(value != null){
            if((int)value > 20000){
                setForeground(Color.RED);
            }
        }
        return fontChange;
    }
}