package fr.doranco.com;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.doranco.com.impl.ImagePanel;

import fr.doranco.com.impl.FiltreBleu;
import fr.doranco.com.impl.FiltreGaussien;
import fr.doranco.com.impl.FiltreNegatif;
import fr.doranco.com.impl.FiltreNoirEtBlanc;
import fr.doranco.com.impl.FiltreSepia;

public class Snapper {
	
	private BufferedImage in;
	private BufferedImage out;
 	
	public void startSnapTiger() {

		final Filtre fBleu = new FiltreBleu();
		final Filtre fSepia = new FiltreSepia();
		final Filtre fNegatif = new FiltreNegatif();
		final Filtre fGaussien = new FiltreGaussien();
		final Filtre fNoirEtBlanc = new FiltreNoirEtBlanc();

		// Creation de la frame, fenetre principale
		JFrame frame = new JFrame("SnapTiger");
		frame.setSize(500, 400);
		frame.setLayout(new BorderLayout());
		
		// Implementation du comportement de fermeture
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// Panel Boutons
		JPanel panelBoutons = new JPanel();
		JButton boutonFiltreBleu = new JButton("Bleu");
		JButton boutonFiltreNegatif = new JButton("Négatif");
		JButton boutonFiltreSepia = new JButton("Sépia");
		JButton boutonFiltreGaussien = new JButton("Gaussien");
		JButton boutonFiltreNoirEtBlanc = new JButton("Noir Et Blanc");

		panelBoutons.add(boutonFiltreBleu);
		panelBoutons.add(boutonFiltreNegatif);
		panelBoutons.add(boutonFiltreSepia);
		panelBoutons.add(boutonFiltreGaussien);
		panelBoutons.add(boutonFiltreNoirEtBlanc);
		
		frame.add(BorderLayout.BEFORE_FIRST_LINE, panelBoutons);
		
		// Panel ImagesContainer
		JPanel imagesContainer = new JPanel();
		imagesContainer.setLayout(new BoxLayout(imagesContainer, BoxLayout.X_AXIS));
		frame.add(BorderLayout.CENTER, imagesContainer);
		
		// Panel
		JPanel imagePanel = new ImagePanel("src/fr/doranco/com/images/logo.jpg");
		imagePanel.setPreferredSize(new Dimension(225, 225));
		imagesContainer.add(imagePanel);
		in = ((ImagePanel) imagePanel).getImage();

		JPanel imagePanelResult = new ImagePanel(in);
		imagePanelResult.setPreferredSize(new Dimension(225, 225));
		imagesContainer.add(imagePanelResult);
		
		// Frame
		// frame.pack();
		frame.setVisible(true);
		
		HashMap<Filtre, JButton> typeFilterMap = new HashMap<>();
		typeFilterMap.put(fBleu, boutonFiltreBleu);
		typeFilterMap.put(fNegatif, boutonFiltreNegatif);
		typeFilterMap.put(fSepia, boutonFiltreSepia);
		typeFilterMap.put(fGaussien, boutonFiltreGaussien);
		typeFilterMap.put(fNoirEtBlanc, boutonFiltreNoirEtBlanc);


		for (Entry<Filtre, JButton> typeFilter  : typeFilterMap.entrySet()) 
		   //  System.out.println("key :" + entry.getKey() + " value " + entry.getValue());
			typeFilter.getValue().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				out = typeFilter.getKey().appliquer(in);
				((ImagePanel) imagePanelResult).setImage(out);
				imagesContainer.revalidate();
			}
		});
		
	}

	public static void main(String[] args) {
		(new Snapper()).startSnapTiger();		
	}
	
}
