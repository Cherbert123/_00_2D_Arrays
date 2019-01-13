package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import javax.swing.JPanel;


public class GridPanel extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	private int pixelWidth;
	private int pixelHeight;
	private int rows;
	private int cols;
	
	private static final String DATA_FILE = "src/_02_Pixel_Art/saved.dat";
	
	//1. Create a 2D array of pixels. Do not initialize it yet.
	Pixel[][] pixels;
	private Color color;
	
	public GridPanel(int w, int h, int r, int c) {
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;
		
		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;
		
		color = Color.WHITE;
		
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		
		//2. Initialize the pixel array using the rows and cols variables.
		pixels = new Pixel[rows][cols];
		
		//3. Iterate through the array and initialize each element to a new pixel.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				pixels[i][j] = new Pixel(i * pixelWidth,j * pixelHeight);
			}
		}
		pixels = load();
		
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void clickPixel(int mouseX, int mouseY) {
		//5. Use the mouseX and mouseY variables to change the color
		//   of the pixel that was clicked. *HINT* Use the pixel's dimensions.
		Random random = new Random();
		pixels[mouseX/pixelWidth][mouseY/pixelHeight].color = color;
	}
	
	private static void save(Pixel[][] data) {
		try (FileOutputStream fos = new FileOutputStream(new File(DATA_FILE)); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Pixel[][] load() {
		try (FileInputStream fis = new FileInputStream(new File(DATA_FILE)); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (Pixel[][]) ois.readObject();
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//4. Iterate through the array.
		//   For every pixel in the list, fill in a rectangle using the pixel's color.
		//   Then, use drawRect to add a grid pattern to your display.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				g.setColor(pixels[i][j].color);
				g.fillRect(pixels[i][j].x, pixels[i][j].y, pixelWidth, pixelHeight);
				g.setColor(Color.BLACK);
				g.drawRect(pixels[i][j].x, pixels[i][j].y, pixelWidth, pixelHeight);
			}
		}
		save(pixels);
	}
}
