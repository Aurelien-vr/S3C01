package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

@SuppressWarnings("serial")
public class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer{

    public MultiLineTableCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        setText(value == null ? "" : value.toString());

        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }

        // Optional: adjust row height to fit text
        setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
        int preferredHeight = getPreferredSize().height;
        if (table.getRowHeight(row) < preferredHeight) {
            table.setRowHeight(row, preferredHeight);
        }
        
        
        JTextArea textArea = new JTextArea(value.toString());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(true);
        textArea.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        textArea.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        textArea.setFont(table.getFont());
        textArea.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        textArea.setAlignmentX(CENTER_ALIGNMENT);
        textArea.setAlignmentY(CENTER_ALIGNMENT);

        return this;
    }
}