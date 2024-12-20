package view;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;

import dao.BienDAO;
import dao.DAOFactory;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.util.List;
import java.awt.Component;
import java.awt.GridLayout;


public class Page_principale extends WindowSkeleton{

	private static final long serialVersionUID = 1L;
	ScrollPane mainPanel = new ScrollPane();
	JPanel headerRow = new JPanel();
	JPanel container = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//SwingUtilities.invokeLater(() -> new Page_CooController());
		SwingUtilities.invokeLater(() -> new Page_principale());
	}

	public Page_principale() {
	    super();
	    
	    
	    
	    JPanel rightFiller = new JPanel();
	    JPanel leftFiller = new JPanel();
	    rightFiller.setBackground(Color.WHITE);
	    leftFiller.setBackground(Color.WHITE);
	    leftFiller.add(Box.createHorizontalStrut(100));
	    rightFiller.add(Box.createHorizontalStrut(100));
	    getContentPane().add(mainPanel, BorderLayout.CENTER);
	    getContentPane().add(leftFiller,BorderLayout.WEST);
	    getContentPane().add(rightFiller,BorderLayout.EAST);

	    String[] columns = {"Header 1", "Header 2", "Header 3"};

	    Object[][] data = new Object[100][3];
	    for (int i = 0; i < 100; i++) {
	        data[i][0] = "Row " + (i + 1) + ", Col 1";
	        data[i][1] = "Row " + (i + 1) + ", Col 2";
	        data[i][2] = "Row " + (i + 1) + ", Col 3";
	    }

	    DefaultTableModel model = new DefaultTableModel(data, columns) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {return false;}
	    };
	    	
	    JTable table = new JTable(model);
	    setTableProperty(table);
	    
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setEnabled(false);
	    scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
	    scrollPane.setBorder(BorderFactory.createEmptyBorder());
	    table.setBorder(BorderFactory.createEmptyBorder());
	    

	    
	    mainPanel.add(scrollPane);
	    setVisible(true);
	}

	private void setTableProperty(JTable table) {
		table.setSelectionBackground(null);
	    table.setSelectionForeground(null);
	    table.setCellSelectionEnabled(true);
	    table.setFocusable(false);
	    table.setBorder(BorderFactory.createEmptyBorder());
	    table.setRowMargin(20);
	    table.setRowHeight(80);
	    table.getTableHeader().setPreferredSize(new Dimension(0,70));
	}


	public void createJpanelWithLabel(JPanel panel,JLabel...jLabels) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for(JLabel label: jLabels) {
			label.setAlignmentX(CENTER_ALIGNMENT);
			label.setFont(new Font(getName(), Font.BOLD, 20));
			panel.add(label);
		}
	}
	
	public JPanel getContainer() {
		return container;
	}
}
