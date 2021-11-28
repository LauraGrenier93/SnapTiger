package fr.doranco.com.impl;

import java.awt.image.BufferedImage;

public class FiltreGaussien extends AbstractFiltre {

	@Override
	public BufferedImage appliquer(BufferedImage image) {

		BufferedImage res = null;
		
		if (image != null) {

			final int width = image.getWidth();
			final int height = image.getHeight();

			res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {

					int p = 0;
					int[][] matrice = getMatricePixels(image, x, y, width, height);

					int a = 255;
					int r = appliquerMatrice(matrice, 'R');
					int g = appliquerMatrice(matrice, 'G');
					int b = appliquerMatrice(matrice, 'B');

					p = (a<<24) | (r<<16) | (g<<8) | b;

					res.setRGB(x, y, p);
				}
			}
		}

		return res;
	}
	
	
	private int appliquerMatrice(int[][] m, char c) {
		double res = 0;
		double k = 1;
		double total = 0;
		
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				total += k;
				
				switch(c) {
				case 'R':
					res += k * getRGBFromPixel(m[i][j], 'R');
					break;
				case 'G':
					res += k * getRGBFromPixel(m[i][j], 'G');
					break;
				default:
					res += k * getRGBFromPixel(m[i][j], 'B');
					break;
				}
			}
		}
		res = res / total;
		return (int) res;
	}

	private int[][] getMatricePixels(BufferedImage bImage, int x, int y, int width, int height) {
		int [][] matrice = new int[5][5];
		
		int pixel = bImage.getRGB(x, y);
		
		for (int i=-2; i<=2; i++)
			for (int j=-2; j<=2; j++) {

				if (x+i<0 || y+j<0 || x+i>width-1 || y+j>height-1) {
					matrice[i+2][j+2] = pixel;
				} else {
					matrice[i+2][j+2] = bImage.getRGB(x+i, y+j);
				}
			}

		return matrice;
	}
	
	private int getRGBFromPixel(int p, char c) {
		switch (c) {
			case 'R':
				return (p>>16) & 0xff;
			case 'G':
				return (p>>8) & 0xff;
			default:
				return p & 0xff;
		}
	}
	
}
