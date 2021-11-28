package fr.doranco.com.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	
	public ImagePanel(String nomFichier) {
		try {
			image = ImageIO.read(new File(nomFichier));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImagePanel(BufferedImage image) {
		setImage(image);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {

		final int width = image.getWidth();
		final int height = image.getHeight();
		
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		for(int y=0; y<height; y++)
			for(int x=0; x<width; x++)
				this.image.setRGB(x, y, image.getRGB(x, y));
		repaint();
	}
	
}