package utilities;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class ScallingDimension {
	public static int scaleValue(int value) {
	    int referenceWidth = 2880;
	    int referenceHeight = 1800;
	    
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        DisplayMode currentMode = device.getDisplayMode();

        int currentWidth = currentMode.getWidth();
        int currentHeight = currentMode.getHeight();
		
	    double widthScaleFactor = (double) currentWidth / referenceWidth;
	    double heightScaleFactor = (double) currentHeight / referenceHeight;
	    double scaleFactor = Math.min(widthScaleFactor, heightScaleFactor);

	    return (int) (value * scaleFactor);
	}
}
