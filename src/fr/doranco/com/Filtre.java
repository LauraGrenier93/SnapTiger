package fr.doranco.com;

import java.awt.image.BufferedImage;

public interface Filtre {
	
	public BufferedImage appliquer(BufferedImage image);
	
	public BufferedImage appliquer(String nomFichier);
	
	public void exporter(BufferedImage bImage, String nomFichier);
	
}
