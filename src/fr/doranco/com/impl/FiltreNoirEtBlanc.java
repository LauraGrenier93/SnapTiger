package fr.doranco.com.impl;

import java.awt.image.BufferedImage;

public class FiltreNoirEtBlanc extends AbstractFiltre {

	@Override
	public BufferedImage appliquer(BufferedImage image) {
		BufferedImage res = null;
		
		
		if (image != null) {
			
			final int width = image.getWidth();
			final int height = image.getHeight();
			
			res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					
					int p = image.getRGB(x,y);
					
					int a = (p>>24) & 0xff;
					
					int r = (p>>16) & 0xff;
					int g = (p>>8) & 0xff;
					int b = p & 0xff;
					int vga = (r+g+b) / 3;
					vga += vga < 156 ? 100 : 0;
					
					p = (a<<24) | (vga<<16) | (vga<<8) | vga;
					
					res.setRGB(x, y, p);
				}
			}
		}
		
		return res;
	}

}
