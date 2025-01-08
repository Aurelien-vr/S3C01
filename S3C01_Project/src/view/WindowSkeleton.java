package view;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import javax.swing.*;

public class WindowSkeleton extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton logoLabel;
	protected JPanel headerPanel;
	
	public WindowSkeleton() {
		FlatLightLaf.setup();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));

        getContentPane().setLayout(new BorderLayout());
				
		headerPanel = new JPanel();
        headerPanel.setBackground(new Color(135, 206, 250));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(700, 100));

       logoLabel = new JButton(new ImageIcon(getClass().getResource("/ressources/logoDBM.png")));

        setTransparencyButton(logoLabel);
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 10));
        headerPanel.add(logoLabel, BorderLayout.WEST); 
        getContentPane().add(headerPanel, BorderLayout.NORTH);
	}

	private static void setTransparencyButton(JButton button) {
		button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPressedIcon(null);
        button.setOpaque(false);
	}
	
	public JButton getLogoLabel() {
		return logoLabel;
	}

}
