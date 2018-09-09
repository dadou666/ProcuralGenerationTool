package gen.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import gen.Carre;
import gen.Composition;
import gen.Element;
import gen.Generateur;
import gen.Placement;
import gen.PlacementFeuille;
import gen.Region;

class PlacementCanvas extends JComponent implements KeyListener {
	public static int width = 600;
	public static int height = 400;

	public Generateur gen;
	public List<PlacementFeuille> list = new ArrayList<>();
	int n=3;
	String main="B";

	public PlacementCanvas(Generateur gen) {
		this.gen = gen;
		Placement p= gen.generer(main, width, height, n);
		list.clear();
		p.donnerPlacementFeuille(list);
		
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		for(PlacementFeuille pf:list) {
			Region region = pf.regionAbsolu();
			Carre carre = (Carre) pf.base;
			g.setColor(carre.color);
			g.fillRect((int)region.x, (int)region.y, (int)region.dx, (int)region.dy);
			System.out.println( region);
			
		}

	}

	static public Generateur gen() {
		Generateur gen;
		try {
			gen = Generateur.lireXML("src/gen.xml");
			gen.bases.put("Rouge", new Carre(Color.red));
			gen.bases.put("Bleue", new Carre(Color.blue));
			gen.bases.put("Vert", new Carre(Color.green));
			return gen;
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}

	public static void main(String[] a) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, width, height);
		PlacementCanvas mc = new PlacementCanvas(gen());
		window.getContentPane().add(mc);
		window.setVisible(true);
		window.addKeyListener(mc);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		Placement p= gen.generer(main, width, height, n);
		list.clear();
		p.donnerPlacementFeuille(list);
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
