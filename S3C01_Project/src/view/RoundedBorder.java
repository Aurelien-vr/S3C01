package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {
	private static final long serialVersionUID = 1L;

    private final int arcWidth;
    private final int arcHeight;
    private final Color borderColor;
    private final Color exteriorColor;

    public RoundedBorder(int arcWidth, int arcHeight, Color borderColor, Color exteriorColor) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.borderColor = borderColor;
        this.exteriorColor = exteriorColor;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(exteriorColor);
        g2.fillRoundRect(x, y, width, height, arcWidth, arcHeight);

        g2.setColor(borderColor);
        g2.drawRoundRect(x, y, width - 1, height - 1, arcWidth, arcHeight);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(arcHeight / 2, arcWidth / 2, arcHeight / 2, arcWidth / 2);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = arcWidth / 2;
        insets.right = arcWidth / 2;
        insets.top = arcHeight / 2;
        insets.bottom = arcHeight / 2;
        return insets;
    }
}
