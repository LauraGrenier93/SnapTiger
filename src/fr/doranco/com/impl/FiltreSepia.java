package fr.doranco.com.impl;

import java.awt.image.BufferedImage;


public class FiltreSepia extends AbstractFiltre {

	BufferedImage res = null;

	@Override
	public BufferedImage appliquer(BufferedImage image) {

		BufferedImage res = null;
		
		if (image != null) {

			final int width = image.getWidth();
			final int height = image.getHeight();
			
			res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			
			for(int y=0; y<height; y++)
				for(int x=0; x<width; x++)
					res.setRGB(x, y, image.getRGB(x, y));

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {

					int p = image.getRGB(x,y);

					int a = (p>>24) & 0xff;

					int r = (p>>16) & 0xff;
					int g = (p>>8) & 0xff;
					int b = p & 0xff;

			        int newR = (int)((0.393*r + 0.769*g + 0.189*b))> 255 ? 255 :(int)((0.393*r + 0.769*g + 0.189*b));
			        int newG = (int)((0.349*r + 0.686*g + 0.168*b)) > 255 ? 255 :(int)((0.349*r + 0.686*g + 0.168*b));;
			        int newB = (int)((0.272*r + 0.534*g + 0.131*b)) > 255 ? 255 :(int)((0.272*r + 0.534*g + 0.131*b));

					p = (a<<24) | (newR<<16) | (newG<<8) | newB;

					res.setRGB(x, y, p);
				}
			}
		}

		return res;
	}

}



