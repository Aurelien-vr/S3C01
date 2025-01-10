package utilities;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import java.awt.Color;

public class CustomMenuUI extends BasicMenuUI {
    @Override
    protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
        // Do nothing to prevent background painting
    }

    @Override
    protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
        // Custom text painting
        g.setColor(menuItem.getForeground());
        g.drawString(text, textRect.x, textRect.y + g.getFontMetrics().getAscent());
    }
    
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        menuItem.setOpaque(false);
    }
}