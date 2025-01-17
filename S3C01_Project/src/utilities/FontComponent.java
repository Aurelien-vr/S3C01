package utilities;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FontComponent {
	
	private FontComponent() {}
    public static void setFontForAllComponents(Container container, int fontSize) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel || component instanceof JTextField ||
                    component instanceof JComboBox || component instanceof JButton || component instanceof JCheckBox
                    || component instanceof JToggleButton) {
                component.setFont(component.getFont().deriveFont((float) fontSize));
            }
            if (component instanceof Container containerIf) {
                setFontForAllComponents(containerIf, fontSize); // Recursive for nested containers
            }

        }
    }
}
