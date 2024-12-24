package view;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.io.File;

public class Page_bienlouable extends JFrame {

    
    public Page_bienlouable() {
        setTitle("Liste des Biens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        getContentPane().setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(135, 206, 250));
        headerPanel.setLayout(new GridLayout(1, 6, 10, 0));

        JLabel logoLabel = new JLabel("LBM");
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerPanel.add(logoLabel);

        JLabel label = new JLabel("Type de tri :");
        headerPanel.add(label);

        JComboBox<String> sortTypeComboBox = new JComboBox<>(new String[]{"Bien louable", "Locataire", "Immeuble"});      
        headerPanel.add(sortTypeComboBox);

        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Appartement", "Garage"});
        headerPanel.add(categoryComboBox);

        JButton addPropertyButton = new JButton("Ajout de bien");
        addPropertyButton.addActionListener(e -> showAddPropertyDialog());
        addPropertyButton.setContentAreaFilled(false);
        addPropertyButton.setBorder(new RoundedBorder(30,30, Color.BLACK), getBackground());
        headerPanel.add(addPropertyButton);

        getContentPane().add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);

        this.mainPanel = mainPanel;
    }

    private final JPanel mainPanel;

    private void showAddPropertyDialog() {
        JTextField departmentField = new JTextField(10);
        JTextField addressField = new JTextField(10);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Département:"));
        inputPanel.add(departmentField);
        inputPanel.add(new JLabel("Adresse:"));
        inputPanel.add(addressField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Ajout de bien",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String department = departmentField.getText();
            String address = addressField.getText();
            if (!department.isEmpty() && !address.isEmpty()) {
                addPropertyPanel(mainPanel, department, address);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addPropertyPanel(JPanel mainPanel, String department, String address) {
        JPanel propertyPanel = new JPanel();
        propertyPanel.setLayout(new BorderLayout(10, 10));
        propertyPanel.setBorder(new RoundedBorder(20, 20, Color.BLACK, getBackground()));
        propertyPanel.setBackground(Color.WHITE);

        propertyPanel.setPreferredSize(new Dimension(700, 120));
        propertyPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        propertyPanel.setMinimumSize(new Dimension(700, 80));

        JLabel imageLabel = new JLabel("Image");
        imageLabel.setPreferredSize(new Dimension(100, 100));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Button to select an image
        JButton selectImageButton = new JButton("Sélectionner une image");
        selectImageButton.addActionListener(e -> selectImage(imageLabel));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));
        textPanel.add(new JLabel(department));
        textPanel.add(new JLabel(address));

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce bien ?", "Confirmation",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                mainPanel.remove(propertyPanel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        propertyPanel.add(imageLabel, BorderLayout.WEST);
        propertyPanel.add(selectImageButton, BorderLayout.NORTH); // Ajout du bouton au panel
        propertyPanel.add(textPanel, BorderLayout.CENTER);
        propertyPanel.add(deleteButton, BorderLayout.EAST);

        mainPanel.add(propertyPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void selectImage(JLabel imageLabel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images", "jpg", "png", "gif"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resize the image
            imageLabel.setIcon(new ImageIcon(image));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Page_bienlouable::new);
    }
}
