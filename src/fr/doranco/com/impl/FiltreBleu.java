package fr.doranco.com.impl;


import java.awt.image.BufferedImage;


public class FiltreBleu extends AbstractFiltre {

	@Override
	public BufferedImage appliquer(BufferedImage image) {
		
		BufferedImage res = null;
		
		if (image != null) {

			final int width = image.getWidth();
			final int height = image.getHeight();
			int p;
			int a;
			int b;
			res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			
			for(int y=0; y<height; y++)
				for(int x=0; x<width; x++)
					res.setRGB(x, y, image.getRGB(x, y));

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
						p = image.getRGB(x, y); // ff ff ff ff = 4 294 967 295 | 255 255 255 255
				
						a = (p>>24) & 0xff; // (ae ff ff ff) >> 24 = 00 00 00 ae, (fe aa c9 ae) & (00 00 00 ff) = ae

						b = p & 0xff;

					p = (a<<24) | (0<<16) | (0<<8) | b;

					res.setRGB(x, y, p);
				}
			}
		}

		return res;
	}

}
