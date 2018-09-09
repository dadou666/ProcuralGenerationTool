package gen.test;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import gen.Carre;
import gen.Composition;
import gen.Element;
import gen.PlacementFeuille;
import gen.Region;

public class CompositionCanvas extends JComponent {
	Composition composition;
	int width;
	int height;

	CompositionCanvas(int width, int height) {
		this.width = width;
		this.height = height;

	}

	public void paint(Graphics g) {
		if (composition == null) {
			return;
		}
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		for (Element elt : composition.elements.values()) {
			for (Region r : elt.regions) {
				int x =(int)( r.x*(float)width);
				int y =(int)( r.y*(float)height);
				int dx =	(int)( r.dx*(float)width);
				int dy =	(int)( r.dy*(float)height);
				g.setColor(Color.BLUE);
				g.drawRect(x, y, dx, dy);
				
			}

		}

	}
}
