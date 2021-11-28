package fr.doranco.com.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.doranco.com.Filtre;

public abstract class AbstractFiltre implements Filtre {
	
	@Override
	public BufferedImage appliquer(String nomFichier) {
		
		BufferedImage image = null;
		
		File file = null;
		try {
			file = new File(nomFichier);
			image = ImageIO.read(file);
		}
		catch(IOException e) {
			System.out.println(e);
		}

		return appliquer(image);
	}
	
	@Override
	public void exporter(BufferedImage bImage, String nomFichier) {
		try {
			File file = new File(nomFichier);
			ImageIO.write(bImage, "png", file);
			System.out.println("Enregistrement réalisé avec succès !");
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}
