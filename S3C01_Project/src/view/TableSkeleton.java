package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.Border;

import java.awt.*;

public class TableSkeleton extends WindowSkeleton {

    private static final long serialVersionUID = 1L;
    private JPanel canvas = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel container = new JPanel();
    private JPanel rightFiller = new JPanel();
    private JPanel leftFiller = new JPanel();
    private JPanel topFiller = new JPanel();
    private JTable table = new JTable();

    public TableSkeleton() {
        super();
        
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

        mainPanel.add(scrollPane);
        getContentPane().add(canvas, BorderLayout.CENTER);
        canvas.add(mainPanel, BorderLayout.CENTER);	
        canvas.add(topFiller, BorderLayout.NORTH);
        canvas.add(leftFiller, BorderLayout.WEST);
        canvas.add(rightFiller, BorderLayout.EAST);

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
        
     
        Border borderHeader =  BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0),new RoundedBorder(10,10, Color.black, new Color(125,125,125,125)));
        table.getTableHeader().setBorder(borderHeader);
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

    public void setTableModel(TableModel model) {
        table.setModel(model);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < table.getColumnCount(); i++) {
        	table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        table.getColumnModel().getColumn(0).setCellRenderer(new MultiLineTableCellRenderer());
        
    }

    public void setMultiLineRenderer(int columnIndex) {
        table.getColumnModel().getColumn(columnIndex).setCellRenderer(new MultiLineTableCellRenderer());
    }
}