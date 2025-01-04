package controller;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;


@SuppressWarnings("serial")
public class ButtonRenderer extends JButton implements TableCellRenderer 
  {
    public ButtonRenderer() {
      setOpaque(true);
      Border lineBorder = new LineBorder(Color.BLACK, 1);
      Border emptyBorder = new EmptyBorder(5, 5, 5, 5);
      setBorder(new CompoundBorder(emptyBorder, lineBorder));
      setBackground(Color.WHITE); // Set fixed background color
      setFocusPainted(false); // Disable focus painting
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {
      setText((value == null) ? "Modify" : value.toString());
      return this;
    }
  }