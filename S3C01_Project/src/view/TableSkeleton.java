package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import controller.ButtonEditor;
import controller.ButtonRenderer;
import controller.ScallingDimension;

import javax.swing.border.Border;

import java.awt.*;

public class TableSkeleton extends PageHeaderSkeleton {

    private static final long serialVersionUID = 1L;
    protected JLayeredPane layeredPane = new JLayeredPane();
    protected JPanel canvas = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel container = new JPanel();
    protected JPanel rightFiller = new JPanel();
    protected JPanel leftFiller = new JPanel();
    protected JPanel topFiller = new JPanel();
    private JTable table = new JTable();
    private JButton deleteButton = new JButton();
    private JLabel footerLabel = new JLabel();
	private JPanel footerPanel = new JPanel();

	Border border =  BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0),new RoundedBorder(10,10, Color.black, new Color(125,125,125,125)));

    public TableSkeleton() {
        super();
        layeredPane.setLayout(null);
        canvas.setLocation(6, 0);
        canvas.setLayout(new BorderLayout());
        
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.setLayout(new BorderLayout());
        createFiller(rightFiller, leftFiller, topFiller);


        setTableProperty(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setEnabled(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        footerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerPanel.add(footerLabel);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        getContentPane().add(layeredPane.add(canvas), BorderLayout.CENTER);
        canvas.add(mainPanel, BorderLayout.CENTER);	
        canvas.add(topFiller, BorderLayout.NORTH);
        canvas.add(leftFiller, BorderLayout.WEST);
        canvas.add(rightFiller, BorderLayout.EAST);
        
        layeredPane.add(canvas, JLayeredPane.DEFAULT_LAYER);
        
        getContentPane().add(layeredPane, BorderLayout.CENTER);
        canvas.setSize(new Dimension(1434, 900 - ScallingDimension.scaleValue(200)));
        setFooterProperty();
    }

	private void setFooterProperty() {
		footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(border);
        getContentPane().setBackground(Color.WHITE);
        footerLabel.setFont(footerLabel.getFont().deriveFont((float) ScallingDimension.scaleValue(24)));
	}

    private void createFiller(JPanel rightFiller, JPanel leftFiller, JPanel topFiller) {
        rightFiller.setBackground(Color.WHITE);
        leftFiller.setBackground(Color.WHITE);
        topFiller.setBackground(Color.WHITE);
        leftFiller.add(Box.createHorizontalStrut(100));
        rightFiller.add(Box.createHorizontalStrut(100));
        topFiller.add(Box.createVerticalStrut(40));
    }

    private void setTableProperty(JTable table) {
        table.setSelectionBackground(null);
        table.setCellSelectionEnabled(true);
        table.setFocusable(false);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setRowMargin(20);
        table.setRowHeight(80);
        table.getTableHeader().setPreferredSize(new Dimension(0, 70));
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setBorder(new RoundedBorder(10,10, Color.black, new Color(0,0,0,0)));
        table.setBackground(Color.WHITE);
        
        table.getTableHeader().setOpaque(false);
        
        table.getTableHeader().setBorder(border);
    }

    public void createJpanelWithLabel(JPanel panel, JLabel... jLabels) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (JLabel label : jLabels) {
            label.setAlignmentX(CENTER_ALIGNMENT);
            label.setFont(new Font(getName(), Font.BOLD, 20));
            panel.add(label);
        }
    }

    public JPanel getContainerStatus() {
        return container;
    }

    public JTable getTable() {
        return table;
    }

    public void setTableModel(TableModel model, int ...multiLineCol) {
        table.setModel(model);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        for(int i : multiLineCol) {        	
            table.getColumnModel().getColumn(i).setCellRenderer(new MultiLineTableCellRenderer());
        }
        
        int lastColumnIndex = table.getColumnCount() - 1;
        if ("SUPPRIMER".equals(table.getColumnName(lastColumnIndex))) {
            table.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(lastColumnIndex).setCellEditor(new ButtonEditor(new JCheckBox(), deleteButton));
        }
    }

    public void updateFooter(String text) {
        footerLabel.setText(text);
    }
    
    public JButton getDeleteButton() {
        return deleteButton;
    }
    
    public void setMultiLineRenderer(int columnIndex) {
        table.getColumnModel().getColumn(columnIndex).setCellRenderer(new MultiLineTableCellRenderer());
    }
    
    public JPanel getCanvas() {
		return canvas;
	}

	public JPanel getFooterPanel() {
		return footerPanel;
	}


}