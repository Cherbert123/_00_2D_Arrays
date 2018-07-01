package _01_Crazy_Digital_Painting;

import java.awt.Color;
import java.util.Random;

public class CrazyDigitalPainting {
	//1. Create two final static integers for the width and height of the display.
	final static int width = 2000;
	final static int height = 1000;
	//2. Create a 2D array of Color objects. You will need to import
	//java.awt.Color. Initialize the size of the array using the 
	//integers created in step 1.
	Color[][] colors = new Color[width][height];
	
	
	
	public CrazyDigitalPainting() {
		//3. Open the crazy_digital_painting.png file and look at the image.
		
		//4. Iterated through the 2D array and initialize each Color object
		//   to a new color. The sample image was created using the following 
		//   pattern:
		//   colors[i][j] = new Color(i % 256, (i * j) % 256, j % 256);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (i==0) {
					Random random = new Random();
					colors[i][j] = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
				} else {
					colors[i][j] = new Color(colors[i-1][j].getRGB()).brighter();
					if (colors[i][j].getGreen() == 255 | colors[i][j].getBlue() == 255 | colors[i][j].getRed() == 255 ) {
						Random random = new Random();
						colors[i][j] = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
					}
				}
			}
		}
		//5. Come up with your own pattern to make a cool crazy image.
		ColorArrayDisplayer runner = new ColorArrayDisplayer();
		runner.displayColorsAsImage(colors);
		//6. Use the ColorArrayDisplayer class to call the displayColorsAsImage method 
		//   to show off your picture.
	}
	
	public static void main(String[] args) {
		new CrazyDigitalPainting();
	}
}
