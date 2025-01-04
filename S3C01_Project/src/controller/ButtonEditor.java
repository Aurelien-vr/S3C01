package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ButtonEditor extends DefaultCellEditor 
{
  private String label;
  private JButton button;
  
  public ButtonEditor(JCheckBox checkBox, JButton button)
  {
    super(checkBox);
    this.button = button;
    Border lineBorder = new LineBorder(Color.BLACK, 1);
    Border emptyBorder = new EmptyBorder(5, 5, 5, 5);
    this.button.setBorder(new CompoundBorder(emptyBorder, lineBorder));
    this.button.setBackground(Color.WHITE);
    this.button.setFocusPainted(false);    
  }
  public Component getTableCellEditorComponent(JTable table, Object value,
	  boolean isSelected, int row, int column) {
	    label = (value == null) ? "Modify" : value.toString();
	    button.setText(label);
	    return button;
	  }
  
	  public Object getCellEditorValue() {
	    return new String(label);
	  }
}
